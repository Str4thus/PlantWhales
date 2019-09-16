package com.example.plantwhales.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.plantwhales.IDisplayable
import com.example.plantwhales.gamelogic.Game
import com.example.plantwhales.gameobjects.GameObject
import com.example.plantwhales.shapes.Shape
import com.example.plantwhales.ui.UIElement

class CanvasView(context: Context) : View(context) {
    var backgroundColor: Paint = Paint()
    var gameObjectsToDraw: ArrayList<IDisplayable> = ArrayList()
    var uiElementsToDraw: ArrayList<IDisplayable> = ArrayList()

    init {
        backgroundColor.setARGB(255, 142, 142, 142)
    }

    override fun onDraw(canvas: Canvas) {
        // Draw GameObjects
        for (gameObject: IDisplayable in gameObjectsToDraw) {
            gameObject.display(canvas)
        }

        // Draw Top Margin
        if (Game.playFieldTopMargin > 0)
            canvas.drawRect(0f, 0f, Game.screenSize.x, Game.playFieldTopMargin, backgroundColor)

        // Draw Bottom Margin
        if (Game.playFieldBottomMargin > 0)
            canvas.drawRect(0f, Game.screenSize.y - Game.playFieldBottomMargin, Game.screenSize.x,Game.screenSize.y, backgroundColor)

        // Draw Left Margin
        if (Game.playFieldLeftMargin > 0)
            canvas.drawRect(0f, Game.playFieldTopMargin, Game.playFieldLeftMargin,Game.screenSize.y - Game.playFieldBottomMargin, backgroundColor)

        // Draw Right Margin
        if (Game.playFieldRightMargin > 0)
            canvas.drawRect(Game.screenSize.x - Game.playFieldRightMargin, Game.playFieldTopMargin, Game.screenSize.x, Game.screenSize.y - Game.playFieldBottomMargin, backgroundColor)

        // Draw UIElements
        for (uiElement: IDisplayable in uiElementsToDraw) {
            uiElement.display(canvas)
        }
    }
}