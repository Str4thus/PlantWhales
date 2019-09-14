package com.example.plantwhales.shapes

import android.graphics.Canvas
import android.graphics.Paint
import com.example.plantwhales.maths.Vector2

abstract class Shape(argb: Array<Int>) {
    var paint: Paint = Paint()
    var isVisible: Boolean = false; protected set

    var becameVisible: Boolean = false; protected set
    var becameInvisible: Boolean = false; protected set

    init {
        if (argb.size < 4)
            throw IllegalArgumentException("ARGB received too less values! Should be an array of 4 integers between 0-255")
        else if (argb.size > 4) {
            throw IllegalArgumentException("ARGB received too much values! Should be an array of 4 integers between 0-255")
        }
        paint.setARGB(argb[0], argb[1], argb[2], argb[3])
    }

    protected abstract fun draw(position: Vector2, canvas: Canvas)
    protected abstract fun checkVisibility(position: Vector2): Boolean

    fun cycle(position: Vector2, canvas: Canvas) {
        becameInvisible = false
        becameVisible = false
        val isCurrentlyVisible = checkVisibility(position)

        if (isCurrentlyVisible && !this.isVisible)
            this.becameVisible = true


        if (!isCurrentlyVisible && this.isVisible)
            this.becameInvisible = true

        this.isVisible = isCurrentlyVisible

        if (this.isVisible)
            draw(position, canvas)
    }
}