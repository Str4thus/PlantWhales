package com.example.plantwhales.collision

import com.example.plantwhales.gamelogic.Physics
import com.example.plantwhales.gameobjects.GameObject

abstract class Collider {
    lateinit var gameObject: GameObject
    var collidingObjects: ArrayList<Collider> = ArrayList(); private set
    var isActive: Boolean = true

    fun overlaps(other: Collider): Boolean {
        return if (isActive) Physics.doOverlap(this, other) else false
    }
}