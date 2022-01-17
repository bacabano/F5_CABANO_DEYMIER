package com.example.tp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch


class LyricsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.title = "Artist Title"
        supportActionBar?.title = "Artist Title"
        setContentView(R.layout.activity_lyrics)

        //Get the url from the MainActivity
        val my_url = intent.getStringExtra("url_shared")

        lifecycleScope.launch{
            val my_lyrics = CallAPI.getLyrics(my_url.toString())
            val txt = findViewById(R.id.text_lyrics) as TextView
            txt.setText(my_lyrics)
        }

    }


}