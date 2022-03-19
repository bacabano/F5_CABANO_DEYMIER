package com.example.tp2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


class SettingsActivity : AppCompatActivity() {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.title = getString(R.string.settings_name)
        supportActionBar?.title = getString(R.string.settings_name)
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

    fun deleteHist(view: View){
        val db = Database(this)
        db.deleteDatabase()
        Toast.makeText(this, getString(R.string.txt_search_deleted), Toast.LENGTH_SHORT).show()
    }

}