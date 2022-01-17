package com.example.tp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.textview.MaterialTextView


class RecentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent)

        val list = mutableListOf(
            "Antique ruby",
            "Bitter lemon"
        )

        fillHistory()

        // initialize an array adapter
        val adapter:ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,list
        )

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

    }

}