package com.example.tp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import java.io.File
import java.lang.Exception


class HistoryActivity : AppCompatActivity() {

    val BASE_URL: String = "https://api.lyrics.ovh/v1/"
    val history_list = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.title = "Historique"
        supportActionBar?.title = "Historique"
        setContentView(R.layout.activity_recent)

        fillHistory()
        // initialize an array adapter
        val adapter:ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,history_list)

        // attach the array adapter with list view
        var listView = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter

        // list view item click listener
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedItem = parent.getItemAtPosition(position).toString()
                val data = selectedItem.split(" - ")
                val monIntent = Intent(this, LyricsActivity::class.java)

                val artist = data[0]
                val title = data[1]
                monIntent.putExtra("artist", artist)
                monIntent.putExtra("title", title)
                monIntent.putExtra("url_shared", "$BASE_URL$artist/$title")
                startActivity(monIntent)
            }
    }
    fun fillHistory(){
        // path : /storage/emulated/0/Android/data/com.example.tp2/files
        val filename = "myData.csv"
        val path = getExternalFilesDir(null)
        try {
            val fileOut = File(path, filename)
            val list = fileOut.readLines()
            val size = list.size

            for (i in size-1 downTo 0) {
                val splitted = list[i].split(',')
                val recent_search = '"' + splitted[0] + '"' + " - " + '"' + splitted[1] + '"'
                history_list.add(recent_search)
            }
        } catch (e: Exception) {
            Log.d("History", e.toString())
        }
    }
}