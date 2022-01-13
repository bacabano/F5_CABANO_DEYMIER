package com.example.tp2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate


class SettingsActivity : AppCompatActivity() {


    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.title = "Settings"
        supportActionBar?.title = "Settings"
        setContentView(R.layout.activity_settings)


        //DARK MODE
        val btn = findViewById<Switch>(R.id.switch1)

        //Handle the state of the switch button
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            btn.isChecked = true
        }

        //Switch themes
        btn.setOnCheckedChangeListener { _, isChecked ->
            if (btn.isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

}