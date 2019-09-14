package com.example.plantwhales.gameobjects

import android.graphics.Canvas
import android.util.Log
import com.example.plantwhales.collision.Collider
import com.example.plantwhales.gamelogic.Game
import com.example.plantwhales.gamelogic.Time
import com.example.plantwhales.maths.Vector2
import com.example.plantwhales.shapes.Shape

abstract class GameObject {
    abstract var collider: Collider protected set
    abstract var shape: Shape protected set

    var position: Vector2 = Vector2(0f, 0f); protected set

    private var started: Boolean = false

    abstract fun start()
    abstract fun cycle()

    abstract fun onCollisionEnter(other: Collider)
    abstract fun onCollision(other: Collider)
    abstract fun onCollisionExit(other: Collider)

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

        val checkedGameObjects: ArrayList<GameObject> = ArrayList()

        for (otherGameObject: GameObject in Game.getGameObjects()) {
            val otherCollider: Collider = otherGameObject.collider

            /** Prevent duplicate collision checking **/
            if (!checkedGameObjects.contains(this) && this != otherGameObject) {
                checkedGameObjects.addAll(arrayOf(this, otherGameObject))

                /** If Collision **/
                if (collider.overlaps(otherCollider)) {
                    if (!this.collider.collidingObjects.contains(otherCollider)) {
                        this.collider.collidingObjects.add(otherCollider)
                        this.onCollisionEnter(otherCollider)
                    }

                    if (!otherCollider.collidingObjects.contains(this.collider)) {
                        otherCollider.collidingObjects.add(this.collider)
                        otherCollider.gameObject.onCollisionExit(this.collider)
                    }
                /** No Collision (anymore) **/
                } else {
                    if (this.collider.collidingObjects.contains(otherCollider)) {
                        this.collider.collidingObjects.remove(otherCollider)
                        this.onCollisionExit(otherCollider)
                    }

                    if (otherCollider.collidingObjects.contains(this.collider)) {
                        otherCollider.collidingObjects.remove(this.collider)
                        otherCollider.gameObject.onCollisionExit(this.collider)
                    }
                }
            }
        }

        for (collider: Collider in this.collider.collidingObjects) {
            this.onCollision(collider)
        }

        checkedGameObjects.clear() // ?
    }
}