package com.example.tp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.io.File


class LyricsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.title = intent.getStringExtra("artist")!!.uppercase() + " - " + intent.getStringExtra("title")!!.uppercase()
        supportActionBar?.title = intent.getStringExtra("artist")!!.uppercase() + " - " + intent.getStringExtra("title")!!.uppercase()
        setContentView(R.layout.activity_lyrics)

        //Get the url from the MainActivity
        val my_url = intent.getStringExtra("url_shared")

        lifecycleScope.launch{
            val txt = findViewById(R.id.text_lyrics) as TextView
            try {
                val my_lyrics = CallAPI.getLyrics(my_url.toString())
                txt.text = my_lyrics

                //Handle history
                val filename = "myData.csv"

                // path : /storage/emulated/0/Android/data/com.example.tp2/files
                val path = getExternalFilesDir(null)
                val fileOut = File(path, filename)

                fileOut.appendText(intent.getStringExtra("artist")!!.uppercase())
                fileOut.appendText(",")
                fileOut.appendText(intent.getStringExtra("title")!!.uppercase())
                fileOut.appendText(",")
                fileOut.appendText("\n")

            } catch (e: Exception) {
                txt.text = "Les lyriczz de cette chanson n'ont pas été trouvés."
            }

        }

    }


}