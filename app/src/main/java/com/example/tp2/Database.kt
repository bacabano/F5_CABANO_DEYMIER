package com.example.tp2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import com.example.tp2.DatabaseDefinition.FeedEntry.COLUMN_NAME_ARTIST
import com.example.tp2.DatabaseDefinition.FeedEntry.COLUMN_NAME_DATE
import com.example.tp2.DatabaseDefinition.FeedEntry.COLUMN_NAME_LYRICZZ
import com.example.tp2.DatabaseDefinition.FeedEntry.COLUMN_NAME_TITLE
import com.example.tp2.DatabaseDefinition.SQL_DELETE_ENTRIES
import io.ktor.util.date.*


object DatabaseDefinition {
    object FeedEntry : BaseColumns {
        const val COLUMN_NAME_ARTIST = "artist"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_LYRICZZ = "lyriczz"
        const val COLUMN_NAME_DATE = "date"
    }

     const val SQL_DELETE_ENTRIES = "DELETE FROM HISTORIC"
}


class Database(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        Log.d("mydatabase", "c'est créé")
        val SQL_CREATE_ENTRIES =
            "CREATE TABLE HISTORIC (" +
                    COLUMN_NAME_ARTIST + " TEXT," +
                    COLUMN_NAME_TITLE + " TEXT," +
                    COLUMN_NAME_LYRICZZ + " TEXT," +
                    COLUMN_NAME_DATE + " LONG);"
        db.execSQL(SQL_CREATE_ENTRIES)
        Log.d("mydatabase", "c'est créé")
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS HISTORIC")
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "HISTORIC_DB"
    }

    fun insertData(artist: String, title: String, lyriczz: String) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NAME_ARTIST, artist)
        contentValues.put(COLUMN_NAME_TITLE, title)
        contentValues.put(COLUMN_NAME_LYRICZZ, lyriczz)
        contentValues.put(COLUMN_NAME_DATE, getTimeMillis())
        val result = database.insert("HISTORIC", null, contentValues)

        if (result == (0).toLong()) {
            Log.d("mydatabase", "failed")
        }
        else {
            Log.d("mydatabase", "success")
        }
        database.close()
    }


    fun readData(): MutableList<Muzzic> {
        val list: MutableList<Muzzic> = ArrayList()
        val db = this.readableDatabase
        val result = db.rawQuery("SELECT * FROM HISTORIC ORDER BY date DESC", null)
        if (result.moveToFirst()) {
            do {
                var res = Muzzic(result.getString(result.getColumnIndexOrThrow(COLUMN_NAME_ARTIST)),
                                 result.getString(result.getColumnIndexOrThrow(COLUMN_NAME_TITLE)),
                                 result.getString(result.getColumnIndexOrThrow(COLUMN_NAME_LYRICZZ)),
                                 result.getLong(result.getColumnIndexOrThrow(COLUMN_NAME_DATE)))
                Log.d("mydatabase", res.toString())
                list.add(res)
            }
            while (result.moveToNext())
            Log.d("mydatabase", list.toString())
            result.close()
        }
        return list
    }

    fun deleteDatabase() {
        val database = this.writableDatabase
        database.execSQL(SQL_DELETE_ENTRIES)
    }

    fun checkHisto(query: String) : Boolean{
        val db = this.readableDatabase
        val result = db.rawQuery(query, null)
        return result.moveToFirst()
    }

    fun selectLyriczz(query: String) : String{
        val db = this.readableDatabase
        val result = db.rawQuery(query, null)
        result.moveToFirst()
        return result.getString(result.getColumnIndexOrThrow(COLUMN_NAME_LYRICZZ))
    }

    fun updateDB(artist: String, title: String){
        val database = this.writableDatabase
        val lyriczz = selectLyriczz("SELECT * FROM HISTORIC " + "WHERE artist = \"" + artist + "\" AND title = \"" + title + "\";")
        val query = "DELETE FROM HISTORIC WHERE artist = \"" + artist + "\" AND title = \"" + title + "\";"
        database.execSQL(query)
        insertData(artist, title, lyriczz)
    }

}
