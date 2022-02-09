package com.example.tp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.io.File


class LyricsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.title = (intent.getStringExtra("artist")!!.uppercase() + " - " + intent.getStringExtra("title")!!.uppercase()).replace('"',' ')
        supportActionBar?.title = (intent.getStringExtra("artist")!!.uppercase() + " - " + intent.getStringExtra("title")!!.uppercase()).replace('"',' ')
        setContentView(R.layout.activity_lyrics)

        //Check if lyriczz are in the database
        val datab = Database(applicationContext)
        val query = "SELECT * FROM HISTORIC " +
                    "WHERE artist = \'" + intent.getStringExtra("artist")!!.uppercase() +
                          "\' AND title = \'" + intent.getStringExtra("title")!!.uppercase() + "\';"
        val txt = findViewById(R.id.text_lyrics) as TextView
        if(!datab.checkHisto(query)){
            //Get the url from the MainActivity
            val my_url = intent.getStringExtra("url_shared")

            lifecycleScope.launch{

                try {
                    val my_lyrics = CallAPI.getLyrics(my_url.toString())
                    txt.text = my_lyrics
                    datab.insertData(intent.getStringExtra("artist")!!.uppercase(), intent.getStringExtra("title")!!.uppercase(), my_lyrics)

                } catch (e: Exception) {
                    txt.text = "Les lyriczz de cette chanson n'ont pas été trouvés."
                }
            }
            Log.d("mydatabase","dans le if")
        } else {
            txt.text=datab.selectLyriczz(query);
            Log.d("mydatabase","dans le else")
        }

    }


}