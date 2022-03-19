package com.example.tp2

import CustomAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.title = getString(R.string.historic_name)
        supportActionBar?.title = getString(R.string.historic_name)
        setContentView(R.layout.activity_recent)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // Read db
        val datab = Database(applicationContext)
        val list: MutableList<Muzzic> = datab.readData()
        list.forEach(){
            data.add(ItemsViewModel(R.drawable.ic_baseline_music_note_24, it.artist + " -- " + it.title))
        }

        val adapter = CustomAdapter(data)
        recyclerview.adapter = adapter
    }
}