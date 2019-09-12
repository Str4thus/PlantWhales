package com.example.plantwhales.gameobjects

import android.util.Log
import com.example.plantwhales.gamelogic.Game
import com.example.plantwhales.gamelogic.InputSystem
import com.example.plantwhales.maths.Vector2
import com.example.plantwhales.shapes.Shape


class Player(override var shape: Shape) : GameObject() {

    override fun start() {
        this.position = Game.screenSize
    }

    override fun update() {
        this.position.x = InputSystem.touchPosition?.x ?: this.position.x
    }
}