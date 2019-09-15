package com.example.plantwhales.gamelogic

import android.app.Activity
import android.os.Build
import android.os.Handler
import android.view.ViewTreeObserver
import com.example.plantwhales.collision.CircleCollider
import com.example.plantwhales.collision.RectCollider
import com.example.plantwhales.gameobjects.*
import com.example.plantwhales.maths.Vector2
import com.example.plantwhales.proto.Proto
import com.example.plantwhales.shapes.Circle
import com.example.plantwhales.shapes.Rect
import com.example.plantwhales.views.CanvasView

object Game {
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

    lateinit var screenSize: Vector2 private set
    lateinit var canvas: CanvasView private set

    var isRunning: Boolean = false
    var isPaused: Boolean = false

    // Game Setup
    private fun init(hostActivity: Activity) {
        /** Creating canvas **/
        canvas = CanvasView(hostActivity.applicationContext)

        val vto = canvas.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                /** Set global Game Properties **/
                screenSize = Vector2(canvas.width.toFloat(), canvas.height.toFloat())


                if (ProtoManager.hasProto("LeftPaddle"))
                    gameObjects.add(ProtoManager.instantiateProto("LeftPaddle", 30f)!!)
                if (ProtoManager.hasProto("RightPaddle"))
                    gameObjects.add(ProtoManager.instantiateProto("RightPaddle", Game.screenSize.x - 30f)!!)
                /*****************************/

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
            /** Create GameObjects that need to be there in the beginning
            // Creating Protos
            val player: Proto<Player> = Proto(GameObject.Type.Player)
            player.shape = Rect(200f, 100f, arrayOf(255, 255, 255, 0))
            player.collider = RectCollider(200f, 100f)
            ProtoManager.addProto("Player", player)

            val projectile: Proto<Projectile> = Proto(GameObject.Type.Projectile)
            projectile.shape = Circle(50f, arrayOf(255, 255, 0, 255))
            projectile.collider = CircleCollider(50f)
            ProtoManager.addProto("Projectile", projectile)

            // Adding
            if (ProtoManager.getProto("Player") != null)
            gameObjects.add(ProtoManager.instantiateProto("Player")!!)
            /***************************************************************/ */


            /** Create GameObjects that need to be there in the beginning **/
            // Creating Protos
            val ball: Proto<Ball> = Proto(GameObject.Type.Ball)
            ball.shape = Circle(50f, arrayOf(255, 255, 0, 255))
            ball.collider = CircleCollider(50f)
            ProtoManager.addProto("Ball", ball)

            val leftPaddle: Proto<Paddle> = Proto(GameObject.Type.Paddle)
            leftPaddle.shape = Rect(30f, 200f, arrayOf(255, 255, 0, 255))
            leftPaddle.collider = RectCollider(30f, 200f)
            ProtoManager.addProto("LeftPaddle", leftPaddle)

            val rightPaddle: Proto<Paddle> = Proto(GameObject.Type.Paddle)
            rightPaddle.shape = Rect(30f, 200f, arrayOf(255, 255, 0, 255))
            rightPaddle.collider = RectCollider(30f, 200f)
            ProtoManager.addProto("RightPaddle", rightPaddle)

            // Adding
            if (ProtoManager.hasProto("Ball"))
                gameObjects.add(ProtoManager.instantiateProto("Ball", 0f)!!)
            /***************************************************************/

            init(hostActivity)
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
        canvas.objectsToDraw = gameObjects
        canvas.invalidate() // redraw
    }

    /** DO NOT ADD GAME OBJECTS VIA THE MAIN THREAD (?) **/
    fun addGameObject(gameObject: GameObject) {
        gameObjects.add(gameObject)
    }

    fun getGameObjects(): ArrayList<GameObject> {
        return gameObjects
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