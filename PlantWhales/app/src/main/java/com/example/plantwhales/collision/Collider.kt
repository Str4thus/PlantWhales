package com.example.plantwhales.collision

import com.example.plantwhales.gamelogic.Physics
import com.example.plantwhales.gameobjects.GameObject

abstract class Collider(var gameObject: GameObject) {
    var collidingObjects: ArrayList<Collider> = ArrayList(); private set

    fun overlaps(other: Collider): Boolean {
        return Physics.doOverlap(this, other)
    }

    operator fun rangeTo(other: Collider): Boolean {
        return overlaps(other)
    }
}