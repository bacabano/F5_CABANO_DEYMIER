package com.example.tp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import java.io.File
import java.lang.Exception


class HistoryActivity : AppCompatActivity() {

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
                val selectedItem = parent.getItemAtPosition(position)
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
                history_list.add(list[i].replace(',', ' '))
            }
        } catch (e: Exception) {
            Log.d("History", e.toString())
        }
    }
}