package com.example.tp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch


class LyricsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.title = "Artist Title"
        supportActionBar?.title = "Artist Title"
        setContentView(R.layout.activity_lyrics)
        lifecycleScope.launch{
            CallAPI.getLyrics("https://api.lyrics.ovh/v1/billie+ellish/lost+cause")
        }

    }


}