package com.example.plantwhales.gameobjects

import com.example.plantwhales.collision.Collider
import com.example.plantwhales.collision.RectCollider
import com.example.plantwhales.gamelogic.Game
import com.example.plantwhales.gamelogic.InputSystem
import com.example.plantwhales.maths.Vector2
import com.example.plantwhales.shapes.Circle
import com.example.plantwhales.shapes.Rect
import com.example.plantwhales.shapes.Shape


class Player(override var shape: Shape) : GameObject() {
    //override var collider: Collider = CircleCollider(this, (shape as Circle).radius)
    override var collider: Collider =
        RectCollider(this, (shape as Rect).width, (shape as Rect).height)

    private var triggered: Boolean = false

    override fun start() {
        this.position = Vector2(Game.screenSize.x / 2, Game.screenSize.y - 50f)
    }

    override fun cycle() {
        this.position.x = InputSystem.touchPosition?.x ?: this.position.x

        if (!triggered && this.position.x > Game.screenSize.x / 2) {
            Game.addGameObject(Projectile(Circle(80f, arrayOf(255, 255, 0, 0))))
            triggered = true
        }
    }

    override fun onCollision(other: Collider) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.}
    }

    override fun onBecameVisible() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBecameInvisible() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.}
    }
}