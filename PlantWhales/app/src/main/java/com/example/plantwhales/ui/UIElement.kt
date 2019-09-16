package com.example.plantwhales.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.plantwhales.IDisplayable
import com.example.plantwhales.ITouchable
import com.example.plantwhales.gamelogic.Game
import com.example.plantwhales.maths.Vector2
import com.example.plantwhales.shapes.Shape

abstract class UIElement(paint: Array<Int>) : Shape(paint), IDisplayable, ITouchable {
    abstract var position: Vector2

    override fun display(canvas: Canvas) {
        this.cycle(position, canvas)
    }
}