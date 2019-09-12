package com.example.plantwhales.maths

import kotlin.math.pow
import kotlin.math.sqrt

data class Vector2(var x: Float, var y: Float) {
    companion object {
        fun dist(a: Vector2, b: Vector2): Float {
            return sqrt( (a.x - b.x).pow(2) + (a.y - b.y).pow(2))
        }
    }

    operator fun plusAssign(b: Vector2) {
        this.x += b.x
        this.y += b.y
    }

    operator fun minusAssign(b: Vector2) {
        this.x -= b.x
        this.y -= b.y
    }

    operator fun timesAssign(scalar: Number) {
        this.x *= scalar.toFloat()
        this.y *= scalar.toFloat()
    }

    operator fun times(scalar: Number) = Vector2(this.x * scalar.toFloat(), this.y * scalar.toFloat())
}