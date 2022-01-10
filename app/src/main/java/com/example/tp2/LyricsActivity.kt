package com.example.tp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LyricsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.title = "Artist Title"
        supportActionBar?.title = "Artist Title"
        setContentView(R.layout.activity_lyrics)
    }
}