package com.example.tp2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.tp2.DatabaseDefinition.FeedEntry.COLUMN_NAME_ARTIST
import com.example.tp2.DatabaseDefinition.FeedEntry.COLUMN_NAME_DATE
import com.example.tp2.DatabaseDefinition.FeedEntry.COLUMN_NAME_LYRICZZ
import com.example.tp2.DatabaseDefinition.FeedEntry.COLUMN_NAME_TITLE
import com.example.tp2.DatabaseDefinition.SQL_DELETE_ENTRIES
import io.ktor.util.date.*


object DatabaseDefinition {

    // Define the columns database
    object FeedEntry : BaseColumns {
        const val COLUMN_NAME_ARTIST = "artist"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_LYRICZZ = "lyriczz"
        const val COLUMN_NAME_DATE = "date"
    }

     const val SQL_DELETE_ENTRIES = "DELETE FROM HISTORIC"
}


class Database(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // Create db
    override fun onCreate(db: SQLiteDatabase) {
        val SQL_CREATE_ENTRIES =
            "CREATE TABLE HISTORIC (" +
                    COLUMN_NAME_ARTIST + " TEXT," +
                    COLUMN_NAME_TITLE + " TEXT," +
                    COLUMN_NAME_LYRICZZ + " TEXT," +
                    COLUMN_NAME_DATE + " LONG);"
        db.execSQL(SQL_CREATE_ENTRIES)
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

    // Insert data into the db
    fun insertData(artist: String, title: String, lyriczz: String) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NAME_ARTIST, artist)
        contentValues.put(COLUMN_NAME_TITLE, title)
        contentValues.put(COLUMN_NAME_LYRICZZ, lyriczz)
        contentValues.put(COLUMN_NAME_DATE, getTimeMillis())
        database.insert("HISTORIC", null, contentValues)
        database.close()
    }

    // Read the whole db order by date
    fun readData(): MutableList<Muzzic> {
        val list: MutableList<Muzzic> = ArrayList()
        val db = this.readableDatabase
        val result = db.rawQuery("SELECT * FROM HISTORIC ORDER BY date DESC", null)
        if (result.moveToFirst()) {
            do {
                val res = Muzzic(result.getString(result.getColumnIndexOrThrow(COLUMN_NAME_ARTIST)),
                                 result.getString(result.getColumnIndexOrThrow(COLUMN_NAME_TITLE)),
                                 result.getString(result.getColumnIndexOrThrow(COLUMN_NAME_LYRICZZ)),
                                 result.getLong(result.getColumnIndexOrThrow(COLUMN_NAME_DATE)))
                list.add(res)
            }
            while (result.moveToNext())
            result.close()
        }
        return list
    }

    // Delete the db
    fun deleteDatabase() {
        val database = this.writableDatabase
        database.execSQL(SQL_DELETE_ENTRIES)
    }

    // Check if the searched song is already in the db, then move it on top
    fun checkHisto(query: String) : Boolean{
        val db = this.readableDatabase
        val result = db.rawQuery(query, null)
        try {
            return result.moveToFirst()
        } finally {
            result.close()
        }
    }

    // Display lyriczz, read it from the db instead of calling API
    fun selectLyriczz(query: String) : String{
        val db = this.readableDatabase
        val result = db.rawQuery(query, null)
        try {
            result.moveToFirst()
            return result.getString(result.getColumnIndexOrThrow(COLUMN_NAME_LYRICZZ))
        } finally {
            result.close()
        }
    }

    // Update when an already searched song is searched again
    fun updateDB(artist: String, title: String){
        val database = this.writableDatabase
        val lyriczz = selectLyriczz("SELECT * FROM HISTORIC WHERE artist = \"$artist\" AND title = \"$title\";")
        val query = "DELETE FROM HISTORIC WHERE artist = \"$artist\" AND title = \"$title\";"
        database.execSQL(query)
        insertData(artist, title, lyriczz)
    }

}
