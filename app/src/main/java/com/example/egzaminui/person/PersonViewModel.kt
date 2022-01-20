package com.example.egzaminui.person

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonViewModel (application: Application): AndroidViewModel(application) {

    val readAllPersons: LiveData<List<Person>>

    private  val repository: PersonRepository

    init{
        val personDao = PersonDatabase.getDatabase(application).personDao()
        repository = PersonRepository(personDao)
        readAllPersons = repository.readAllPersons
    }
    fun addPerson(person: Person){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertPerson(person)
        }
    }
    fun updatePerson(person: Person){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePerson(person)
        }
    }
}