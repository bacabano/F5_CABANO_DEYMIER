package com.example.tp2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    val BASE_URL: String = "https://api.lyrics.ovh/v1/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.title = getString(R.string.app_name)
        supportActionBar?.title = getString(R.string.app_name)
        setContentView(R.layout.activity_main)
        Database(this)
    }

    fun createURL(): String {
        val artist: String = (findViewById<EditText>(R.id.get_artist).text).toString()
        val title: String = (findViewById<EditText>(R.id.get_title).text).toString()
        val n_artist = artist.replace(' ', '+')
        val n_title = title.replace(' ', '+')

        return "$BASE_URL$n_artist/$n_title"
    }


    // --------------- NAVIGATE TO ACTIVITIES (LYRICS, SETTINGS, HISTORY)

    fun goToLyricsActivity(view: View){
        val monIntent = Intent(this, LyricsActivity::class.java)
        val artist: String = (findViewById<EditText>(R.id.get_artist).text).toString()
        val title: String = (findViewById<EditText>(R.id.get_title).text).toString()

        if (artist == "" || title == "") {
            Toast.makeText(this, getString(R.string.txt_user_input_toast), Toast.LENGTH_SHORT).show()
        }
        else {
            //Send the url to the LyricsActivity
            monIntent.putExtra("url_shared", createURL())
            monIntent.putExtra("artist", artist)
            monIntent.putExtra("title", title)
            startActivity(monIntent)
        }

    }

    fun goToSettingsActivity(view: View){
        val monIntent = Intent(this, SettingsActivity::class.java)
        startActivity(monIntent)
    }

    fun goToRecentActivity(view: View){
        val monIntent = Intent(this, HistoryActivity::class.java)
        startActivity(monIntent)
    }

}