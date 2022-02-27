package com.example.tp2

import android.util.Log
import androidx.lifecycle.ViewModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*


@Serializable
data class Lyrics(val lyrics: String)

class CallAPI: ViewModel() {

    companion object {
        suspend fun getLyrics(my_url: String): String {
            Log.d("mydatabase", my_url)
            val client = HttpClient()
            val my_lyrics: HttpResponse = client.request(my_url)
            val str: String = my_lyrics.receive()

            val obj = Json.decodeFromString<Lyrics>(str)
            return obj.lyrics
        }
    }

}