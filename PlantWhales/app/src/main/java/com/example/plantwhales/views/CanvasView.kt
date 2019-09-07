package com.example.plantwhales.views

import android.content.Context
import android.graphics.Canvas
import android.view.View
import com.example.plantwhales.gameobjects.GameObject

class CanvasView(context: Context) : View(context) {
    var objectsToDraw: ArrayList<GameObject> = ArrayList()

    override fun onDraw(canvas: Canvas) {
        for (gameObject: GameObject in objectsToDraw) {
            gameObject.display(canvas)
        }
    }
}