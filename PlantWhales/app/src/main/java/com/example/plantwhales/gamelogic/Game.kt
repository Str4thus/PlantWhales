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
    private val gameObjects: ArrayList<GameObject> = ArrayList()
    val canvas: CanvasView = CanvasView(hostActivity.applicationContext)

    private val loopHandler: Handler = Handler()
    private val gameLoop: Runnable = object: Runnable {
        override fun run() {
            update()
            draw()
            loopHandler.post(this)
        }
    }



    fun start() {
        init()
        hostActivity.setContentView(canvas)
        loopHandler.post(gameLoop)
    }

    fun addGameObject(obj: GameObject) {
        gameObjects.add(obj)
    }

    // Game Setup
    // TODO find better solution for this
    private fun init() {
        val vto = canvas.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                // Player Creation (elsewhere the canvas dimensions can't be accessed, width and height are 0)
                val player = Player(Vector2(canvas.width / 2f, canvas.height - 50f), Circle(50f, arrayOf(255, 255, 255, 0)))
                gameObjects.add(player)


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