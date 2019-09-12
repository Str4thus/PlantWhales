package com.example.plantwhales.collision

import com.example.plantwhales.gameobjects.GameObject
import com.example.plantwhales.maths.Vector2

abstract class Collider(var gameObject: GameObject) {
    abstract fun overlaps(other: Collider): Boolean

    operator fun rangeTo(other: Collider): Boolean {
        return overlaps(other)
    }
}