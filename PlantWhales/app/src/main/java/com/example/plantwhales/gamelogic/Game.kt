package com.example.plantwhales.gamelogic

import android.app.Activity
import android.os.Build
import android.os.Handler
import android.util.Log
import android.view.ViewTreeObserver
import com.example.plantwhales.gameobjects.GameObject
import com.example.plantwhales.gameobjects.Player
import com.example.plantwhales.maths.Vector2
import com.example.plantwhales.shapes.Circle
import com.example.plantwhales.views.CanvasView

class Game(private val hostActivity: Activity) {
    companion object {
        lateinit var screenSize: Vector2
    }

    private val gameObjects: ArrayList<GameObject> = ArrayList()
    private val canvas: CanvasView = CanvasView(hostActivity.applicationContext)

    private val loopHandler: Handler = Handler()
    private val gameLoop: Runnable = object: Runnable {
        override fun run() {
            update()
            draw()
            loopHandler.post(this)
        }
    }

    fun start() {
        init(Player(Circle(5f, arrayOf(255, 255, 0, 255))))
        hostActivity.setContentView(canvas)
        loopHandler.post(gameLoop)
    }

    fun addGameObject(obj: GameObject) {
        gameObjects.add(obj)
    }

    // Game Setup
    // TODO find better solution for this
    private fun init(vararg objectsToInit: GameObject) {
        val vto = canvas.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                // Set Screen Size
                screenSize = Vector2(canvas.width.toFloat(), canvas.height.toFloat())

                // Initialize GameObjects
                for (gameObject: GameObject in objectsToInit) {
                    gameObjects.add(gameObject)
                }

                // Call Start on GameObjects
                for (gameObject: GameObject in gameObjects) {
                    gameObject.start()
                }

                val obs = canvas.viewTreeObserver
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    obs.removeOnGlobalLayoutListener(this)
                } else {
                    obs.removeGlobalOnLayoutListener(this)
                }
            }
        })
    }

    // Game Logic
    private fun update() {
        for (gameObject: GameObject in gameObjects) {
            gameObject.update()
        }
    }

    // Display Objects
    private fun draw() {
        canvas.objectsToDraw = this.gameObjects
        canvas.invalidate() // redraws
    }
}