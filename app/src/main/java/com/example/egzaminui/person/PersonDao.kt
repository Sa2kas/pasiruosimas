package com.example.egzaminui.person

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDao {
    @Insert
    fun insert(person: Person?)

    @Query("DELETE FROM Person")
    fun deleteAll()

    @get:Query("SELECT * from Person ORDER BY first_name ASC")
    val allPersons: List<Person?>?
}