package com.example.plantwhales.gameobjects

import android.graphics.Canvas
import android.util.Log
import com.example.plantwhales.IDisplayable
import com.example.plantwhales.collision.Collider
import com.example.plantwhales.gamelogic.Game
import com.example.plantwhales.gamelogic.Time
import com.example.plantwhales.maths.Vector2
import com.example.plantwhales.shapes.Shape

abstract class GameObject : IDisplayable {
    /** Factory **/
    companion object {
        fun create(type: Type): GameObject {
            return when (type) {
                Type.Player -> Player()
                Type.Projectile -> Projectile()
            }
        }
    }
    enum class Type {
        Player,
        Projectile
    }

    /** Properties **/
    var collider: Collider? = null; protected set
    var shape: Shape? = null; protected set
    var position: Vector2 = Vector2(0f, 0f); protected set
    private var started: Boolean = false

    /** Game Loop **/
    abstract fun start()
    abstract fun cycle()

    /** Collision Callbacks**/
    open fun onCollisionEnter(other: Collider) {}
    open fun onCollision(other: Collider) {}
    open fun onCollisionExit(other: Collider) {}

    /** Visibility Callbacks **/
    open fun onBecameVisible() {}
    open fun onBecameInvisible() {
        Game.requestDelete(this)
    }

    /** Inherited **/
    override fun display(canvas: Canvas)  {
        if (started)
            shape?.cycle(position, canvas)
    }

    /** External **/
    fun update() {
        if (!started) {
            start()
            started = true
        }

        if (shape?.becameVisible == true)
            onBecameVisible()

        if (shape?.becameInvisible == true)
            onBecameInvisible()

        cycle()
    }

    fun checkForCollisions() {
        if (!started)
            return

        val thisCollider: Collider = this.collider ?: return // Check if collider is attached

        val checkedGameObjects: ArrayList<GameObject> = ArrayList()
        for (otherGameObject: GameObject in Game.getGameObjects()) {
            val otherCollider: Collider = otherGameObject.collider ?: return

            /** Prevent duplicate collision checking **/
            if (!checkedGameObjects.contains(this) && this != otherGameObject) {
                checkedGameObjects.addAll(arrayOf(this, otherGameObject))

                /** If Collision **/
                if (thisCollider.overlaps(otherCollider)) {
                    if (!thisCollider.collidingObjects.contains(otherCollider)) {
                        thisCollider.collidingObjects.add(otherCollider)
                        this.onCollisionEnter(otherCollider)
                    }

                    if (!otherCollider.collidingObjects.contains(thisCollider)) {
                        otherCollider.collidingObjects.add(thisCollider)
                        otherCollider.gameObject.onCollisionEnter(thisCollider)
                    }
                /** No Collision (anymore) **/
                } else {
                    if (thisCollider.collidingObjects.contains(otherCollider)) {
                        thisCollider.collidingObjects.remove(otherCollider)
                        this.onCollisionExit(otherCollider)
                    }

                    if (otherCollider.collidingObjects.contains(thisCollider)) {
                        otherCollider.collidingObjects.remove(thisCollider)
                        otherCollider.gameObject.onCollisionExit(thisCollider)
                    }
                }
            }
        }

        for (collider: Collider in thisCollider.collidingObjects) {
            this.onCollision(collider)
        }

        checkedGameObjects.clear() // ?
    }

    fun attachCollider(collider: Collider) {
        collider.gameObject = this
        this.collider = collider
    }

    fun attachShape(shape: Shape) {
        this.shape = shape
    }

}