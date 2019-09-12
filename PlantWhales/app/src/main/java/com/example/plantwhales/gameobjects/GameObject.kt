package com.example.plantwhales.gameobjects

import android.graphics.Canvas
import com.example.plantwhales.maths.Vector2
import com.example.plantwhales.shapes.Shape

abstract class GameObject {
    protected var position: Vector2 = Vector2(0f, 0f)
    protected abstract var shape: Shape

    abstract fun start()
    abstract fun update()

    fun display(canvas: Canvas)  {
        shape.draw(position, canvas)
    }
}