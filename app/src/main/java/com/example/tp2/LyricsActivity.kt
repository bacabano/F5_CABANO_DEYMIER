package com.example.tp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileWriter


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
                txt.setText(my_lyrics)

                //Handle recent search
                val fileName = "data.txt"

                var file = File(fileName)

                // create a new file
                val isNewFileCreated :Boolean = file.createNewFile()
                //var fileWriter = FileWriter("./recent_search.csv")
                //val fileWriter = FileWriter(File("app", "recent_search.csv"))
                //fileWriter.append(intent.getStringExtra("artist")!!.uppercase())
                //fileWriter.append(',')
                //fileWriter.append(intent.getStringExtra("title")!!.uppercase())
                //fileWriter.append('\n')


            } catch (e: Exception) {
                txt.setText("Les lyriczz de cette chanson n'ont pas été trouvés." + e)
            }

        }

    }


}