package com.example.plantwhales.gameobjects

import com.example.plantwhales.gamelogic.Game
import com.example.plantwhales.gamelogic.Time
import com.example.plantwhales.maths.Vector2
import com.example.plantwhales.shapes.Shape
import kotlin.random.Random

class Projectile(override var shape: Shape) : GameObject() {
    private val r: Random = Random(84309)
    private val moveSpeed: Float = 50f

    override fun start() {
        val xPos: Float = r.nextDouble(0.0, Game.screenSize.x.toDouble()).toFloat()
        this.position = Vector2(xPos, 50f)
    }

    override fun cycle() {
        this.position.y += moveSpeed * Time.deltaTime
    }
}