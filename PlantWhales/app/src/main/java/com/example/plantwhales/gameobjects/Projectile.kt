package com.example.plantwhales.gameobjects

import com.example.plantwhales.collision.Collider
import com.example.plantwhales.gamelogic.Game
import com.example.plantwhales.gamelogic.Physics
import com.example.plantwhales.gamelogic.Time
import com.example.plantwhales.maths.Vector2
import kotlin.random.Random

class Projectile : GameObject() {
    private val r: Random = Random(Time.currentTime())
    private var speedMultiplier: Float = 4.5f

    override fun start() {
        this.position = Vector2(Game.screenSize.x / 2, 50f)
    }

    override fun cycle() {
        this.position += Physics.gravity * speedMultiplier * Time.deltaTime
    }

    override fun onCollisionEnter(other: Collider) {
        Game.pause()
    }
}