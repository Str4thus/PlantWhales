package com.example.plantwhales.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import com.example.plantwhales.gamelogic.Game

class VirtualStick(context: Context) : UIElement(context) {
    private var paint: Paint = Paint()

    init {
        paint.setARGB(255, 255, 0, 255)
    }

    override fun onDraw(canvas: Canvas) {
        //canvas.drawRect(0f, Game.screenSize.y-50f, Game.screenSize.x, Game.screenSize.y, paint)
    }
}