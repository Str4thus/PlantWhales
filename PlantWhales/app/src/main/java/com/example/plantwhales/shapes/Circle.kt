package com.example.plantwhales.shapes

import android.graphics.Canvas
import com.example.plantwhales.gamelogic.Game
import com.example.plantwhales.maths.Vector2

class Circle(var radius: Float, argb: Array<Int>) : Shape(argb) {

    override fun draw(position: Vector2, canvas: Canvas) {
        canvas.drawCircle(position.x, position.y, this.radius, this.paint)
    }

    override fun checkVisibility(position: Vector2): Boolean {
        var visibility: Boolean = true

        if ((position.x + radius) < Game.playFieldLeft
            || (position.x - radius) > Game.playFieldRight
            || (position.y + radius) < Game.playFieldTop
            || (position.y - radius) > Game.playFieldBottom) {
            visibility = false
        }

        return visibility
    }
}