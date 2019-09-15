package com.example.plantwhales.gameobjects

import com.example.plantwhales.gamelogic.Game
import com.example.plantwhales.gamelogic.InputSystem
import com.example.plantwhales.gamelogic.ProtoManager
import com.example.plantwhales.maths.Vector2


class Player : GameObject() {
    private var triggered: Boolean = false

    override fun start() {
        this.position = Vector2(Game.screenSize.x / 2, Game.screenSize.y - 50f)
    }

    override fun cycle() {
        this.position.x = InputSystem.touchPosition?.x ?: this.position.x

        if (!triggered && this.position.x > Game.screenSize.x / 2) {
            if (ProtoManager.hasProto("Projectile")) {
                Game.addGameObject(ProtoManager.instantiateProto("Projectile")!!)
            }

            triggered = true
        }
    }

}