package com.example.plantwhales.gameobjects

import com.example.plantwhales.collision.Collider
import com.example.plantwhales.gamelogic.Game
import com.example.plantwhales.gamelogic.Time
import com.example.plantwhales.maths.Vector2

class Paddle : GameObject() {
    var xPos: Float = 0f

    override fun start() {
        position = Vector2(xPos, Game.screenSize.y / 2)
    }

    override fun cycle() {
        position.y = Game.getGameObjects()[0].position.y
    }
}