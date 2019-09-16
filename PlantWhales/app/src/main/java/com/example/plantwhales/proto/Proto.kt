package com.example.plantwhales.proto

import com.example.plantwhales.collision.Collider
import com.example.plantwhales.gamelogic.Game
import com.example.plantwhales.gameobjects.GameObject
import com.example.plantwhales.gameobjects.Player
import com.example.plantwhales.shapes.Shape
import kotlin.reflect.KClass

class Proto<T : GameObject>(var type: GameObject.Type) {
    var collider: Collider? = null
    var shape: Shape? = null

    fun instantiate(): T {
        val gameObject: T = GameObject.create(type) as T

        if (collider != null)
            gameObject.attachCollider(collider!!)

        if (shape != null)
            gameObject.attachShape(shape!!)

        return gameObject
    }
}