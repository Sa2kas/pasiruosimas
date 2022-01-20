package com.example.egzaminui.person

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.egzaminui.R
import kotlinx.android.synthetic.main.fragment_person.*
import kotlinx.android.synthetic.main.fragment_person.view.*

private lateinit var personVM: PersonViewModel

class PersonFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_person, container, false)

        //recyclerview
        val adapter = ListAdapter()
        val recyclerView = view.recyclerList
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //WorkoutViewModel
        personVM = ViewModelProvider(this).get(PersonViewModel::class.java)
        personVM.readAllPersons.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

        view.buttonAdd.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val name = editName.text.toString()
        val surname = editSurname.text.toString()
        val number = editNumber.text.toString()

        if (inputCheck(name, surname, number)){
            //create person
            val person = Person(0, name, surname, number)
            //add person to database
            personVM.addPerson(person)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_LONG).show()

        }
        else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private  fun inputCheck(name: String, surname: String, number: String): Boolean{
        return  !(TextUtils.isEmpty(name) || (TextUtils.isEmpty(surname) || (TextUtils.isEmpty(number))))
    }

}