package com.example.plantwhales.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageView
import com.example.plantwhales.gamelogic.Game
import com.example.plantwhales.gamelogic.InputSystem
import com.example.plantwhales.maths.Vector2

class VirtualStick(var width: Float, var height: Float, paint: Array<Int>) : UIElement(paint) {
    override var position = Vector2(Game.screenSize.x / 2, Game.screenSize.y / 2)

    var colors: ArrayList<Paint> = ArrayList()
    var active: Boolean = false

    init {
        var fpaint: Paint = Paint()
        fpaint.setARGB(255, 255, 0, 0)
        colors.add(fpaint)

        fpaint = Paint()
        fpaint.setARGB(255, 0, 255, 0)
        colors.add(fpaint)
    }

    override fun draw(position: Vector2, canvas: Canvas) {
        val x1: Float = (position.x - width/2)
        val y1: Float = (position.y + height/2)

        val x2: Float = (position.x + width/2)
        val y2: Float = (position.y - height/2)

        canvas.drawRect(x1, y1, x2, y2, this.paint)
    }

    override fun checkVisibility(position: Vector2): Boolean {
        return true
    }

    override fun onTouch(event: MotionEvent) {
        if (event.action == MotionEvent.ACTION_DOWN) {
            if (active)
                this.paint = colors[0]
            else
                this.paint = colors[1]
            active = !active
        }
    }

    override fun pointLiesInside(point: Vector2): Boolean {
        val x1: Float = (position.x - width/2)
        val y1: Float = (position.y + height/2)
        val bottomLeft = Vector2(x1, y1)

        val x2: Float = (position.x + width/2)
        val y2: Float = (position.y - height/2)
        val topRight = Vector2(x2, y2)


        return (point.x > bottomLeft.x && point.x < topRight.x
                && point.y < bottomLeft.y && point.y > topRight.y)
    }
}