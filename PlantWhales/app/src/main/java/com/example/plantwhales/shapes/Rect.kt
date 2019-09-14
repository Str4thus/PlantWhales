package com.example.plantwhales.shapes

import android.graphics.Canvas
import com.example.plantwhales.gamelogic.Game
import com.example.plantwhales.maths.Vector2

class Rect(var width: Float, var height: Float, argb: Array<Int>) : Shape(argb) {
    lateinit var bottomLeft: Vector2
    lateinit var topRight: Vector2

    override fun draw(position: Vector2, canvas: Canvas) {
        val x1: Float = (position.x - width/2)
        val y1: Float = (position.y + height/2)
        this.bottomLeft = Vector2(x1, y1)

        val x2: Float = (position.x + width/2)
        val y2: Float = (position.y - height/2)
        this.topRight = Vector2(x2, y2)

        canvas.drawRect(bottomLeft.x, bottomLeft.y, topRight.x, topRight.y, this.paint)
    }

    override fun checkVisibility(position: Vector2): Boolean {
        var visibility: Boolean = true

        if ((position.x + width / 2) < 0
            || (position.x - width / 2) > Game.screenSize.x
            || (position.y + height / 2) < 0
            || (position.y - height / 2) > Game.screenSize.y) {
            visibility = false
        }

        return visibility
    }
}