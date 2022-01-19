package com.example.egzaminui.person

import android.app.Application
import androidx.room.Room


public class AppPersonActivity : Application() {

    var db: PersonDatabase? = null

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, PersonDatabase::class.java, "my_app_db")
            .allowMainThreadQueries().build()
    }

    fun getDatabase(): PersonDatabase? {
        return db
    }
}