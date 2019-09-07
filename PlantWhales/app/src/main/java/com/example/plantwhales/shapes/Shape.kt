package com.example.plantwhales.shapes

import android.graphics.Canvas
import android.graphics.Paint
import com.example.plantwhales.maths.Vector2

abstract class Shape(argb: Array<Int>) {
    var paint: Paint = Paint()

    init {
        if (argb.size < 4)
            throw IllegalArgumentException("ARGB received too less values! Should be an array of 4 integers between 0-255")
        else if (argb.size > 4) {
            throw IllegalArgumentException("ARGB received too much values! Should be an array of 4 integers between 0-255")
        }
        paint.setARGB(argb[0], argb[1], argb[2], argb[3])
    }

    abstract fun draw(position: Vector2, canvas: Canvas)
}