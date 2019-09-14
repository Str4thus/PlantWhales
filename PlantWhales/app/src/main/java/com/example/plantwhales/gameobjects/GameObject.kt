package com.example.plantwhales.gameobjects

import android.graphics.Canvas
import com.example.plantwhales.collision.Collider
import com.example.plantwhales.gamelogic.Game
import com.example.plantwhales.maths.Vector2
import com.example.plantwhales.shapes.Shape

abstract class GameObject {
    abstract var collider: Collider protected set
    abstract var shape: Shape protected set

    var position: Vector2 = Vector2(0f, 0f); protected set

    private var started: Boolean = false

    abstract fun start()
    abstract fun cycle()
    abstract fun onCollision(other: Collider)
    abstract fun onBecameVisible()
    abstract fun onBecameInvisible()

    fun update() {
        if (!started) {
            start()
            started = true
        }

        if (shape.becameVisible)
            onBecameVisible()

        if (shape.becameInvisible)
            onBecameInvisible()

        cycle()
    }

    fun display(canvas: Canvas)  {
        shape.cycle(position, canvas)
    }

    fun checkForCollisions() {
        if (!started)
            return

        for (otherGameObject: GameObject in Game.getGameObjects()) {
            if (this != otherGameObject && collider.overlaps(otherGameObject.collider)) {
                this.onCollision(otherGameObject.collider)
            }
        }
    }
}