package com.example.plantwhales.gamelogic

import com.example.plantwhales.collision.CircleCollider
import com.example.plantwhales.collision.Collider
import com.example.plantwhales.collision.RectCollider
import com.example.plantwhales.maths.Vector2
import kotlin.math.abs
import kotlin.math.pow

object Physics {
    var gravity: Vector2 = Vector2(1f, 9.81f); private set

    fun doOverlap(a: Collider, b: Collider): Boolean {
        /** Circle + Circle **/
        if (a is CircleCollider && b is CircleCollider)
            return (Vector2.dist(a.gameObject.position, b.gameObject.position) < (a.radius + b.radius))

        /** Rect + Rect **/
        else if (a is RectCollider && b is RectCollider) {
            // Rect A
            var x1: Float = (a.gameObject.position.x - a.width/2)
            var y1: Float = (a.gameObject.position.y + a.height/2)
            val bottomLeftA = Vector2(x1, y1)

            var x2: Float = (a.gameObject.position.x + a.width/2)
            var y2: Float = (a.gameObject.position.y - a.height/2)
            val topRightA = Vector2(x2, y2)


            // Rect B
            x1 = (b.gameObject.position.x - b.width/2)
            y1 = (b.gameObject.position.y + b.height/2)
            val bottomLeftB = Vector2(x1, y1)

            x2 = (b.gameObject.position.x + b.width/2)
            y2 = (b.gameObject.position.y - b.height/2)
            val topRightB = Vector2(x2, y2)


            if (topRightA.y < bottomLeftB.y
                || bottomLeftA.y > topRightB.y)
                return false

            if (topRightA.x < bottomLeftB.x
                || bottomLeftA.x > topRightB.x)
                return false

            return true
        }

        /** Rect + Circle **/
        else if (a is CircleCollider && b is RectCollider) {
            val circleDistance: Vector2 = Vector2(abs(a.gameObject.position.x  - b.gameObject.position.x),
                                                    abs(a.gameObject.position.y  - b.gameObject.position.y))

            if (circleDistance.x > (b.width / 2 + a.radius)
                || circleDistance.y > (b.height / 2 + a.radius))
                return false

            if (circleDistance.x <= (b.width/2)
                || circleDistance.y <= (b.height/2))
                return true

            val cornerDistanceSquare = (circleDistance.x - b.width/2).pow(2) + (circleDistance.y - b.height/2).pow(2)
            return cornerDistanceSquare <= a.radius.pow(2)
        }

        /** Circle + Rect **/
        else if (a is RectCollider && b is CircleCollider) {
            val circleDistance: Vector2 = Vector2(abs(b.gameObject.position.x  - a.gameObject.position.x),
                abs(b.gameObject.position.y  - a.gameObject.position.y))

            if (circleDistance.x > (a.width / 2 + b.radius)
                || circleDistance.y > (a.height / 2 + b.radius))
                return false

            if (circleDistance.x <= (a.width/2)
                || circleDistance.y <= (a.height/2))
                return true

            val cornerDistanceSquare = (circleDistance.x - a.width/2).pow(2) + (circleDistance.y - a.height/2).pow(2)
            return cornerDistanceSquare <= b.radius.pow(2)
        }

        return false
    }
}