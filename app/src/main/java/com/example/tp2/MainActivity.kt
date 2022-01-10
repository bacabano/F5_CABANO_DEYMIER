package com.example.tp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.title = "LyricZZ"
        supportActionBar?.title = "LyricZZ"
        setContentView(R.layout.activity_main)
    }

    fun goToLyricsActivity(view: View){
        val button_search: Button = findViewById(R.id.button_search) as Button
        val monIntent = Intent(this, LyricsActivity::class.java)
        startActivity(monIntent)
    }
}