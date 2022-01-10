package com.example.tp2

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*


//@Serializable
data class Lyrics(val lyrics: String)

class CallAPI {

    companion object {
        suspend fun getLyrics(my_url: String): String {
            val client = HttpClient()
            val my_lyrics: HttpResponse = client.request(my_url)
            val str: String = my_lyrics.receive()
            Log.d("CallAPI", str)
            return str

        }
    }

}