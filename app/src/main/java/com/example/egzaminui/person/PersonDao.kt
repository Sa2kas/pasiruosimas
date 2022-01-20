package com.example.egzaminui.person

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PersonDao {
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPerson(person: Person)

    @Query("DELETE FROM person_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM person_table ORDER BY name ASC")
    fun readAllPersons(): LiveData<List<Person>>

    @Update
    suspend fun updatePerson(person: Person)
}