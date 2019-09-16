package com.example.plantwhales

import android.view.MotionEvent
import com.example.plantwhales.maths.Vector2

interface ITouchable {
    fun onTouch(event: MotionEvent)
    fun pointLiesInside(point: Vector2): Boolean
}