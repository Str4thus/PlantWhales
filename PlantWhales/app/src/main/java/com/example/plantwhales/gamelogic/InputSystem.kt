package com.example.plantwhales.gamelogic

import android.view.MotionEvent
import com.example.plantwhales.maths.Vector2

class InputSystem {
    companion object {
        var touchPosition: Vector2? = null

        fun handle(event: MotionEvent) {
            touchPosition = Vector2(event.x, event.y)
        }
    }
}