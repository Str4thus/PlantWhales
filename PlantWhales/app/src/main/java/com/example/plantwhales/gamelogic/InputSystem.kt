package com.example.plantwhales.gamelogic

import android.util.Log
import android.view.MotionEvent
import com.example.plantwhales.ITouchable
import com.example.plantwhales.gameobjects.GameObject
import com.example.plantwhales.maths.Vector2

object InputSystem {
    var touchPosition: Vector2? = null
    var touched: Boolean = false

    fun handleTouchEvent(event: MotionEvent) {
        val canvasPositionInActivity: IntArray = IntArray(2)
        Game.canvas.getLocationOnScreen(canvasPositionInActivity)

        val eventPosition: Vector2 = Vector2(event.x - canvasPositionInActivity[0].toFloat(),
                                             event.y - canvasPositionInActivity[1].toFloat())

        for (uiElement: ITouchable in Game.getUIElements()) {
            if (uiElement.pointLiesInside(eventPosition)) {
                uiElement.onTouch(event)
                return
            }
        }

        for (gameObject: GameObject in Game.getGameObjects()) {
            if (gameObject is ITouchable && (gameObject as ITouchable).pointLiesInside(eventPosition)) {
                (gameObject as ITouchable).onTouch(event)
                return
            }
        }
    }
}