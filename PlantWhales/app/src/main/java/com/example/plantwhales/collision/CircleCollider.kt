package com.example.plantwhales.collision

import com.example.plantwhales.gameobjects.GameObject
import com.example.plantwhales.maths.Vector2
import com.example.plantwhales.maths.Vector2.Companion.dist

class CircleCollider(gameObject: GameObject, var radius: Float) : Collider(gameObject) {

    override fun overlaps(other: Collider): Boolean {
        when (other) {
            is CircleCollider -> {
                return (dist(this.gameObject.position, other.gameObject.position) < (this.radius + other.radius))
            }
        }

        return false
    }
}