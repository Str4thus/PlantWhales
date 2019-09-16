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
        //this.position = Vector2(Game.playFieldRight/ 2, Game.playFieldTop + (shape as Circle).radius)
        this.position = Vector2(Game.playFieldRight/ 2, Game.playFieldBottom / 2)
    }

    override fun cycle() {
        this.position += Vector2(10f, 0f) * -speedMultiplier * Time.deltaTime
    }

    override fun onCollisionEnter(other: Collider) {
        Game.pause()
    }

    override fun onBecameInvisible() {
        Game.pause()
    }
}