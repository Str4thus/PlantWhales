package com.example.plantwhales


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MotionEvent
import com.example.plantwhales.gamelogic.Game
import com.example.plantwhales.gamelogic.InputSystem

/* TODO Prototype-System */
class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }

    override fun onResume() {
        super.onResume()
        Game.unpause()
    }

    override fun onPause() {
        super.onPause()
        Game.pause()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        InputSystem.handle(event)
        return false
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        /** Start the Game **/
        Game.start(this)
        return super.onPrepareOptionsMenu(menu)
    }
}
