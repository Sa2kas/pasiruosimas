package com.example.egzaminui.person

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.Room


class PersonRepository(private val personDao: PersonDao) {

    val readAllPersons: LiveData<List<Person>> = personDao.readAllPersons()

    suspend fun insertPerson(person: Person){
        personDao.insertPerson(person)
    }

    suspend fun updatePerson(person: Person){
        personDao.updatePerson(person)
    }

    suspend fun deleteAll(){
        personDao.deleteAll()
    }
}