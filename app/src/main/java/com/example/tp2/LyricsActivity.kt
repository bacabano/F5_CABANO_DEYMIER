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
        actionBar?.title = intent.getStringExtra("artist") + " " + intent.getStringExtra("title")
        supportActionBar?.title = intent.getStringExtra("artist") + " " + intent.getStringExtra("title")
        setContentView(R.layout.activity_lyrics)

        //Get the url from the MainActivity
        val my_url = intent.getStringExtra("url_shared")

        lifecycleScope.launch{
            val txt = findViewById(R.id.text_lyrics) as TextView
            try {
                val my_lyrics = CallAPI.getLyrics(my_url.toString())
                txt.setText(my_lyrics)
            } catch (e: Exception) {
                txt.setText("Les lyriczz de cette chanson n'ont pas été trouvés.")
            }

        }

    }


}