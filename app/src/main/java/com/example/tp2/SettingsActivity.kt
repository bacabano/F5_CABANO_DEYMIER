package com.example.tp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.title = "Settings"
        supportActionBar?.title = "Settings"
        setContentView(R.layout.activity_settings)
    }
}