package com.example.plantwhales.maths

data class Vector2(var x: Float, var y: Float) {
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
}