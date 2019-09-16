package com.example.plantwhales.gamelogic

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Handler
import android.view.View
import android.view.ViewTreeObserver
import com.example.plantwhales.IDisplayable
import com.example.plantwhales.collision.CircleCollider
import com.example.plantwhales.collision.RectCollider
import com.example.plantwhales.gameobjects.GameObject
import com.example.plantwhales.gameobjects.Player
import com.example.plantwhales.gameobjects.Projectile
import com.example.plantwhales.maths.Vector2
import com.example.plantwhales.proto.Proto
import com.example.plantwhales.shapes.Circle
import com.example.plantwhales.shapes.Rect
import com.example.plantwhales.shapes.Shape
import com.example.plantwhales.ui.UIElement
import com.example.plantwhales.ui.VirtualStick
import com.example.plantwhales.views.CanvasView

object Game {
    private val uiElements: ArrayList<UIElement> = ArrayList() // UI Elements
    private val gameObjects: ArrayList<GameObject> = ArrayList() // About 225 Objects run smooth

    private val loopHandler: Handler = Handler()
    private val gameLoop: Runnable = object: Runnable {
        private var firstLoop: Boolean = true
        private var time: Long = 0L
        private var lastTime: Long = 0L

        override fun run() {
            if (firstLoop) { // Prevent extraordinary high first delta time
                time = Time.currentTime()
                lastTime = time
                firstLoop = false
            }
            // Calculate delta time
            time = Time.currentTime()
            Time.setDeltaTime((time - lastTime))
            lastTime = time

            if (!isPaused) {
                freeObjects()
                checkForCollisions()
                freeObjects()

                update()

                checkForCollisions()
                freeObjects()

                draw()
            }

            loopHandler.post(this)
        }
    }

    private val objectsToDelete: ArrayList<GameObject> = ArrayList()

    var isRunning: Boolean = false; private set
    var isPaused: Boolean = false; private set

    var playFieldLeftMargin: Float = 0f; private set
    var playFieldRightMargin: Float = 0f; private set
    var playFieldTopMargin: Float = 0f; private set
    var playFieldBottomMargin: Float = 200f; private set

    var playFieldBottom: Float = -1f; private set
    var playFieldTop: Float = -1f; private set
    var playFieldLeft: Float = -1f; private set
    var playFieldRight: Float = -1f; private set

    lateinit var playFieldSize: Vector2 private set // Restraint Space (applied Margins)

    lateinit var screenSize: Vector2 private set // Whole Canvas
    lateinit var canvas: CanvasView private set


    private fun initializeUI () {
        val virtualStick: VirtualStick = VirtualStick(100f, arrayOf(255, 255, 0, 255))
        uiElements.add(virtualStick)
    }

    private fun initializeGameObjects() {
        // Player
        val player: Proto<Player> = Proto(GameObject.Type.Player)
        player.shape = Rect(200f, 100f, arrayOf(255, 255, 255, 0))
        player.collider = RectCollider(200f, 100f)
        ProtoManager.addProto("Player", player)

        // Projectile
        val projectile: Proto<Projectile> = Proto(GameObject.Type.Projectile)
        projectile.shape = Circle(50f, arrayOf(255, 255, 0, 255))
        projectile.collider = CircleCollider(50f)
        ProtoManager.addProto("Projectile", projectile)

        if (ProtoManager.getProto("Player") != null)
            gameObjects.add(ProtoManager.instantiateProto("Player")!!)
    }

    // Game Setup
    private fun init(context: Context) {
        /** Creating canvas **/
        canvas = CanvasView(context)
        val vto = canvas.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                /** Set global Game Properties **/
                screenSize = Vector2(canvas.width.toFloat(), canvas.height.toFloat())

                playFieldSize = Vector2(canvas.width.toFloat() - (playFieldRightMargin + playFieldLeftMargin),
                    canvas.height.toFloat() - (playFieldTopMargin + playFieldBottomMargin))

                playFieldTop = playFieldTopMargin
                playFieldBottom = screenSize.y - playFieldBottomMargin
                playFieldLeft = playFieldLeftMargin
                playFieldRight = screenSize.x - playFieldRightMargin
                /*****************************/


                /** Create UI Elements that need to be there in the beginning **/
                initializeUI()
                /***************************************************************/

                val obs = canvas.viewTreeObserver
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    obs.removeOnGlobalLayoutListener(this)
                } else {
                    obs.removeGlobalOnLayoutListener(this)
                }
            }
        })
    }

    // Game Start
    fun start(hostActivity: Activity) {
        if (!isRunning) {
            /** Create GameObjects that need to be there in the beginning **/
            initializeGameObjects()
            /***************************************************************/

            init(hostActivity.applicationContext)
            hostActivity.setContentView(canvas)

            loopHandler.post(gameLoop)
            isRunning = true
        }
    }

    fun pause() {
        if (this.isRunning)
            this.isPaused = true
    }

    fun unpause() {
        if (this.isRunning)
            this.isPaused = false
    }

    // Collision Checking
    private fun checkForCollisions() {
        for (gameObject: GameObject in gameObjects) {
            gameObject.checkForCollisions()
        }
    }

    // Game Logic
    private fun update() {
        for (gameObject: GameObject in gameObjects) {
            gameObject.update()
        }
    }

    // Display Objects
    private fun draw() {
        canvas.gameObjectsToDraw = this.gameObjects as ArrayList<IDisplayable> // safe I guess
        canvas.uiElementsToDraw = this.uiElements as ArrayList<IDisplayable> // safe I guess
        canvas.invalidate() // redraw
    }

    /** DO NOT ADD GAME OBJECTS VIA THE MAIN THREAD (?) **/
    fun addGameObject(gameObject: GameObject) {
        gameObjects.add(gameObject)
    }

    fun getGameObjects(): ArrayList<GameObject> {
        return gameObjects
    }


    /** DO NOT ADD UI ELEMENTS VIA THE MAIN THREAD (?) **/
    fun addUIElement(uiElement: UIElement) {
        uiElements.add(uiElement)
    }

    fun getUIElements(): ArrayList<UIElement> {
        return uiElements
    }


    /** Delete objects that requested to be deleted **/
    fun freeObjects() {
        for (gameObject: GameObject in this.objectsToDelete) {
            gameObjects.remove(gameObject)
        }

        this.objectsToDelete.clear()
    }

    fun requestDelete(gameObject: GameObject) {
        this.objectsToDelete.add(gameObject)
    }
}