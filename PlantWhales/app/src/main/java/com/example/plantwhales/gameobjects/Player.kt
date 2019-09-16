package com.example.plantwhales.gameobjects

import com.example.plantwhales.gamelogic.Game
import com.example.plantwhales.gamelogic.InputSystem
import com.example.plantwhales.gamelogic.ProtoManager
import com.example.plantwhales.maths.Vector2
import com.example.plantwhales.shapes.Rect


class Player : GameObject() {
    private var triggered: Boolean = false

    override fun start() {
        this.position = Vector2(Game.playFieldSize.x / 2, Game.playFieldBottom - (shape as Rect).height / 2)
    }

    override fun cycle() {
        this.position.x = InputSystem.touchPosition?.x ?: this.position.x

        if (!triggered && this.position.x > Game.playFieldSize.x / 2) {
            if (ProtoManager.hasProto("Projectile")) {
                Game.addGameObject(ProtoManager.instantiateProto("Projectile")!!)
            }

            triggered = true
        }
    }

}