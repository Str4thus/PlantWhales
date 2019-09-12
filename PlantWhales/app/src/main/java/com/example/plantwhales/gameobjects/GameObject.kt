package com.example.plantwhales.gameobjects

import android.graphics.Canvas
import com.example.plantwhales.maths.Vector2
import com.example.plantwhales.shapes.Shape

abstract class GameObject {
    protected abstract var shape: Shape
    protected var position: Vector2 = Vector2(0f, 0f)
    private var started: Boolean = false

    abstract fun start()
    abstract fun cycle()

    fun update() {
        if (!started) {
            start()
            started = true
        }

        cycle()
    }

    fun display(canvas: Canvas)  {
        shape.draw(position, canvas)
    }
}