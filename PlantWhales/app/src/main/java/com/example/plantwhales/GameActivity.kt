package com.example.plantwhales


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MotionEvent
import com.example.plantwhales.gamelogic.Game
import com.example.plantwhales.gamelogic.InputSystem

class GameActivity : AppCompatActivity() {
    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        game = Game(this)
        game.start()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        InputSystem.handle(event)
        return false
    }
}
