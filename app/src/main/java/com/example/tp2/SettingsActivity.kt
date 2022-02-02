package com.example.tp2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import java.io.File


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

    fun deleteHist(view: View){
        //Handle history
        val filename = "myData.csv"

        // path : /storage/emulated/0/Android/data/com.example.tp2/files
        val path = getExternalFilesDir(null)
        val fileOut = File(path, filename)
        fileOut.delete()
        Toast.makeText(this, "Recherches récentes supprimées.", Toast.LENGTH_SHORT).show()
    }

}