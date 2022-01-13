package com.example.tp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText


class MainActivity() : AppCompatActivity() {

    val BASE_URL: String = "https://api.lyrics.ovh/v1/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.title = "LyricZZ"
        supportActionBar?.title = "LyricZZ"
        setContentView(R.layout.activity_main)
    }

    fun createURL(): String {
        val artist: String = (findViewById<EditText>(R.id.get_artist).text).toString()
        val title: String = (findViewById<EditText>(R.id.get_title).text).toString()
        val n_artist = artist.replace(' ', '+')
        val n_title = title.replace(' ', '+')

        return "$BASE_URL$n_artist/$n_title"
    }

    fun goToLyricsActivity(view: View){
        val monIntent = Intent(this, LyricsActivity::class.java)

        //Send the url to the LyricsActivity
        monIntent.putExtra("url_shared", createURL())
        startActivity(monIntent)
    }

    fun goToSettingsActivity(view: View){
        val monIntent = Intent(this, SettingsActivity::class.java)
        startActivity(monIntent)
    }

}