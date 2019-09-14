package com.example.plantwhales.gameobjects

import android.util.Log
import com.example.plantwhales.collision.CircleCollider
import com.example.plantwhales.collision.Collider
import com.example.plantwhales.gamelogic.Game
import com.example.plantwhales.gamelogic.Physics
import com.example.plantwhales.gamelogic.Time
import com.example.plantwhales.maths.Vector2
import com.example.plantwhales.shapes.Circle
import com.example.plantwhales.shapes.Shape
import kotlin.random.Random

class Projectile(override var shape: Shape) : GameObject() {
    override var collider: Collider = CircleCollider(this, (shape as Circle).radius)
    private val r: Random = Random(Time.currentTime())
    private var speedMultiplier: Float = 20.5f

    override fun start() {
        val xPos: Float = r.nextDouble(0.0, Game.screenSize.x.toDouble()).toFloat()
        this.position = Vector2(xPos, 50f)
    }

    override fun cycle() {
        this.position += Physics.gravity * speedMultiplier * Time.deltaTime
    }

    override fun onCollisionEnter(other: Collider) {
        Game.pause()
    }

    override fun onCollision(other: Collider) {
    }

    override fun onCollisionExit(other: Collider) {
    }

    override fun onBecameVisible() {}
    
    override fun onBecameInvisible() {}
}