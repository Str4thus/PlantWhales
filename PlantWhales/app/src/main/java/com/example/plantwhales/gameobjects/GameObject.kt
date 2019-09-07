package com.example.plantwhales.gameobjects

import android.graphics.Canvas
import com.example.plantwhales.maths.Vector2
import com.example.plantwhales.shapes.Shape

abstract class GameObject {
    protected abstract var position: Vector2
    protected abstract var shape: Shape

    abstract fun update()

    fun display(canvas: Canvas)  {
        shape.draw(position, canvas)
    }
}