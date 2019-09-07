package com.example.plantwhales.gamelogic

import android.app.Activity
import android.os.Handler
import com.example.plantwhales.gameobjects.GameObject
import com.example.plantwhales.views.CanvasView

class Game(private val hostActivity: Activity) {
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
        hostActivity.setContentView(canvas)

        loopHandler.post(gameLoop)
    }

    fun addGameObject(obj: GameObject) {
        gameObjects.add(obj)
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
        canvas.invalidate()
    }


}