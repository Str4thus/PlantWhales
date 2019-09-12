package com.example.plantwhales.gamelogic

import android.view.MotionEvent
import com.example.plantwhales.maths.Vector2

object InputSystem {
    var touchPosition: Vector2? = null
    var touched: Boolean = false

    fun handle(event: MotionEvent) {
        touchPosition = Vector2(event.x, event.y)

        when (event.action) {
            MotionEvent.ACTION_DOWN -> touched = true
            MotionEvent.ACTION_UP -> touched = false
        }
    }
}