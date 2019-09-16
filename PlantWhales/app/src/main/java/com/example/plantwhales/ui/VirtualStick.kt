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

class VirtualStick(var radius: Float, paint: Array<Int>) : UIElement(paint) {
    override var position = Vector2(Game.screenSize.x / 2, Game.screenSize.y - radius)
    var normalPosition = position
    var isDragged: Boolean = false

    override fun draw(position: Vector2, canvas: Canvas) {
        /*val x1: Float = (position.x - width/2)
        val y1: Float = (position.y + height/2)

        val x2: Float = (position.x + width/2)
        val y2: Float = (position.y - height/2)

        canvas.drawRect(x1, y1, x2, y2, this.paint)*/
        canvas.drawCircle(position.x, position.y, radius, paint)
    }

    override fun checkVisibility(position: Vector2): Boolean {
        return true
    }

    override fun onTouch(event: MotionEvent) {
        if (event.action == MotionEvent.ACTION_OUTSIDE) {
            position = normalPosition
            isDragged = false
        }

        if (event.action == MotionEvent.ACTION_UP) {
            position = normalPosition
            isDragged = false
        }

        if (event.action == MotionEvent.ACTION_MOVE) {
            isDragged = true
            position = Vector2(event.x, normalPosition.y)
        }
    }

    override fun pointLiesInside(point: Vector2): Boolean {
        /*
        val x1: Float = (position.x - width/2)
        val y1: Float = (position.y + height/2)
        val bottomLeft = Vector2(x1, y1)

        val x2: Float = (position.x + width/2)
        val y2: Float = (position.y - height/2)
        val topRight = Vector2(x2, y2)


        return (point.x > bottomLeft.x && point.x < topRight.x
                && point.y < bottomLeft.y && point.y > topRight.y)*/

        return (Vector2.dist(point, position) < radius)
    }
}