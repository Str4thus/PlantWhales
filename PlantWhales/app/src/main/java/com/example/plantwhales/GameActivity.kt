package com.example.plantwhales

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plantwhales.gamelogic.Game
import com.example.plantwhales.gameobjects.Player
import com.example.plantwhales.maths.Vector2
import com.example.plantwhales.shapes.Circle

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val game: Game = Game(this)
        val player: Player = Player(Vector2(100f, 100f), Circle(10f, arrayOf(255, 255, 255, 0)))
        game.addGameObject(player)
        game.start()
    }
}
