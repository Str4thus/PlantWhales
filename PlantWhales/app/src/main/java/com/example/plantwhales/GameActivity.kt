package com.example.plantwhales


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MotionEvent
import android.view.Window
import android.view.WindowManager
import com.example.plantwhales.gamelogic.Game
import com.example.plantwhales.gamelogic.InputSystem

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        supportActionBar!!.hide() // hide the title bar
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
        InputSystem.handleTouchEvent(event)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        /** Start the Game **/
        Game.start(this)
        return super.onPrepareOptionsMenu(menu)
    }
}
