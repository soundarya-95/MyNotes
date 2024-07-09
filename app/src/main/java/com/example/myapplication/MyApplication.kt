package com.example.myapplication

import android.app.Application
import androidx.room.Room
import com.example.myapplication.utils.Constants
import com.example.myapplication.data.db.database.NoteDatabase

class MyApplication : Application() {

    companion object {
        lateinit var database: NoteDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java,
            Constants.DATABASE_NAME
        )
            .build()
    }
}