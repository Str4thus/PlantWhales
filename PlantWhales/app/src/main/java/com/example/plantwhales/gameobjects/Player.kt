package com.example.plantwhales.gameobjects

import com.example.plantwhales.collision.CircleCollider
import com.example.plantwhales.collision.Collider
import com.example.plantwhales.gamelogic.Game
import com.example.plantwhales.gamelogic.InputSystem
import com.example.plantwhales.maths.Vector2
import com.example.plantwhales.shapes.Circle
import com.example.plantwhales.shapes.Shape


class Player(override var shape: Shape) : GameObject() {
    override var collider: Collider = CircleCollider(this, (shape as Circle).radius)
    var triggerd: Boolean = false

    override fun start() {
        this.position = Vector2(Game.screenSize.x / 2, Game.screenSize.y - 50f)
    }

    override fun cycle() {
        this.position.x = InputSystem.touchPosition?.x ?: this.position.x

        if (!triggerd && this.position.x > Game.screenSize.x / 2) {
            Game.addGameObject(Projectile(Circle(80f, arrayOf(255, 255, 0, 0))))
            triggerd = true
        }
    }

    override fun onCollision(other: Collider) {

    }
}