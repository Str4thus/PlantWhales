package com.example.plantwhales.gameobjects

import android.util.Log
import com.example.plantwhales.collision.Collider
import com.example.plantwhales.gamelogic.Game
import com.example.plantwhales.gamelogic.Physics
import com.example.plantwhales.gamelogic.Time
import com.example.plantwhales.maths.Vector2

class Ball : GameObject() {
    private var direction: Vector2 = Vector2(1f, 2f)
    private var speedfactor = 6f
    private var movespeed = 120f

    override fun start() {
        position = Vector2(Game.screenSize.x / 2, Game.screenSize.y / 2)
    }

    override fun cycle() {
        Log.d("Deltatime:" , "${Time.deltaTime}")
        this.position += direction * movespeed * speedfactor * Time.deltaTime
    }

    override fun onBecameInvisible() {
        if (position.y < 0 || position.y > Game.screenSize.y)
            direction.y *= -1
    }

    override fun onCollisionEnter(other: Collider) {
        direction.x *= -1
    }
}