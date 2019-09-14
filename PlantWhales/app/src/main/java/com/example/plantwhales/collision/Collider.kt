package com.example.plantwhales.collision

import com.example.plantwhales.gamelogic.Physics
import com.example.plantwhales.gameobjects.GameObject
import com.example.plantwhales.maths.Vector2

abstract class Collider(var gameObject: GameObject) {
    fun overlaps(other: Collider): Boolean {
        return Physics.doOverlap(this, other)
    }

    operator fun rangeTo(other: Collider): Boolean {
        return overlaps(other)
    }
}