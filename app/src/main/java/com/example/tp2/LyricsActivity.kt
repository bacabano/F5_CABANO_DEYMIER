package com.example.tp2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch


class LyricsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.title = (intent.getStringExtra("artist")!!.uppercase() + " - " + intent.getStringExtra("title")!!.uppercase()).replace('"',' ')
        supportActionBar?.title = (intent.getStringExtra("artist")!!.uppercase() + " - " + intent.getStringExtra("title")!!.uppercase()).replace('"',' ')
        setContentView(R.layout.activity_lyrics)

        //Check if lyriczz are in the database
        val datab = Database(applicationContext)
        val query = "SELECT * FROM HISTORIC " +
                    "WHERE artist = \"" + intent.getStringExtra("artist")!!.uppercase() +
                          "\" AND title = \"" + intent.getStringExtra("title")!!.uppercase() + "\";"
        val txt = findViewById<TextView>(R.id.text_lyrics)
        if(!datab.checkHisto(query)){
            //Get the url from the MainActivity
            val my_url = intent.getStringExtra("url_shared")

            lifecycleScope.launch{

                try {
                    val my_lyrics = CallAPI.getLyrics(my_url.toString())
                    txt.text = my_lyrics
                    datab.insertData(intent.getStringExtra("artist")!!.uppercase(), intent.getStringExtra("title")!!.uppercase(), my_lyrics)

                } catch (e: Exception) {
                    txt.text = getString(R.string.txt_notfound)
                }
            }
        } else {
            datab.updateDB(intent.getStringExtra("artist")!!.uppercase(),intent.getStringExtra("title")!!.uppercase())
            txt.text=datab.selectLyriczz(query)
        }

    }


}