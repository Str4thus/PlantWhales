package com.example.plantwhales.gamelogic

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Handler
import android.util.Log
import android.view.ViewTreeObserver
import com.example.plantwhales.gameobjects.GameObject
import com.example.plantwhales.gameobjects.Player
import com.example.plantwhales.maths.Vector2
import com.example.plantwhales.shapes.Circle
import com.example.plantwhales.views.CanvasView

object Game {
    private val gameObjects: ArrayList<GameObject> = ArrayList()
    private val loopHandler: Handler = Handler()
    private val gameLoop: Runnable = object: Runnable {
        private var time: Long = 0L
        private var lastTime: Long = 0L

        override fun run() {
            // Calculate delta time
            time = Time.currentTime()
            Time.setDeltaTime((time - lastTime))
            lastTime = time

            if (!isPaused) {
                update()
                draw()
            }

            loopHandler.post(this)
        }
    }

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
            /** Create GameObjects that need to be there in the beginning **/
            gameObjects.add(Player(Circle(50f, arrayOf(255, 255, 0, 255))))
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
}