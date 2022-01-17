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

class CallAPI {

    companion object {
        suspend fun getLyrics(my_url: String): String {
            val client = HttpClient()
            val my_lyrics: HttpResponse = client.request(my_url)
            val str: String = my_lyrics.receive()

            //val data = Lyrics(str)
            //val string = Json.encodeToString(data)
            //Log.d("lyrics", string)
            val obj = Json.decodeFromString<Lyrics>(str)
            Log.d("lyrics", obj.lyrics)
            return obj.lyrics

        }
    }

}