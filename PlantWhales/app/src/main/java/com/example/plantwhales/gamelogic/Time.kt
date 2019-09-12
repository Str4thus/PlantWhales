package com.example.plantwhales.gamelogic

object Time {
    var startTime: Long = System.currentTimeMillis(); private set
    var deltaTime: Float = 0f; private set

    var defaultSpeedFactor: Float = 10f; private set
    private var speedFactor: Float = defaultSpeedFactor

    fun setDeltaTime(dt: Long) {
        this.deltaTime = (dt.toFloat() / 1000) * speedFactor
    }

    fun setSpeedFactor(speedFactor: Float) {
        this.speedFactor = speedFactor
    }

    fun resetSpeedFactor() {
        this.speedFactor = this.defaultSpeedFactor
    }

    fun currentTime(): Long {
        return System.currentTimeMillis()
    }
}