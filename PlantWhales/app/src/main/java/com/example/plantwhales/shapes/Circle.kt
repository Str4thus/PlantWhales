package com.example.plantwhales.shapes

import android.graphics.Canvas
import com.example.plantwhales.maths.Vector2

class Circle(var radius: Float, argb: Array<Int>) : Shape(argb) {

    override fun draw(position: Vector2, canvas: Canvas) {
        canvas.drawCircle(position.x, position.y, this.radius, this.paint)
    }
}