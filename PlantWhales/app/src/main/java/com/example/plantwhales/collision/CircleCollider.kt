package com.example.plantwhales.collision

import com.example.plantwhales.gameobjects.GameObject
import com.example.plantwhales.maths.Vector2
import com.example.plantwhales.maths.Vector2.Companion.dist

class CircleCollider(gameObject: GameObject, var radius: Float) : Collider(gameObject) {
}