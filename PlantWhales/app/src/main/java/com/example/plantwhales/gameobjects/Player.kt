package com.example.plantwhales.gameobjects

import com.example.plantwhales.maths.Vector2
import com.example.plantwhales.shapes.Shape

class Player(override var position: Vector2, override var shape: Shape) : GameObject() {

    override fun update() {
        this.position += Vector2(5f, 0f)
    }
}