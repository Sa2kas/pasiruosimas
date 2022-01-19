
package com.example.egzaminui.person

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.egzaminui.R

private var db: PersonDatabase? = null
private var personsListTextView: TextView? = null
private var button: Button? = null
private var firstNameEditText: EditText? = null
private var lastNameEditText: EditText? = null
private var phoneNumberEditText: EditText? = null

class Activity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)

        db = AppPersonActivity.getDatabase()
        personsListTextView = findViewById<View>(R.id.txt_list) as TextView
        firstNameEditText = findViewById<View>(R.id.edittext_name) as EditText
        lastNameEditText = findViewById<View>(R.id.edittext_surname) as EditText
        phoneNumberEditText = findViewById<View>(R.id.edittext_phone) as EditText
        button = findViewById<View>(R.id.button) as Button

        button!!.setOnClickListener(View.OnClickListener {
            val name = firstNameEditText!!.getText().toString().trim { it <= ' ' }
            val surname = lastNameEditText!!.getText().toString().trim { it <= ' ' }
            val phoneNumber = phoneNumberEditText!!.getText().toString().trim { it <= ' ' }
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(surname)
                || TextUtils.isEmpty(phoneNumber)
            ) {
                Toast.makeText(
                    applicationContext,
                    "Name/Surname/Phone Number should not be empty",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val person = Person()
                person.setName(name)
                person.setSurname(surname)
                person.setPhoneNumber(phoneNumber)
                db.personDAO().insert(person)
                Toast.makeText(
                    applicationContext,
                    "Saved successfully",
                    Toast.LENGTH_SHORT
                ).show()
                firstNameEditText.setText("")
                lastNameEditText.setText("")
                phoneNumberEditText.setText("")
                firstNameEditText.requestFocus()
                getPersonList()
            }
        })

        button.setCompoundDrawablesWithIntrinsicBounds(
            R.drawable.ic_save, 0, 0, 0
        )
    }

    private fun getPersonList() {
        personsListTextView!!.text = ""
        val personList: List<Person> = db.PersonDao().getAllPersons()
        for (person: Person in personList) {
            personsListTextView!!.append(
                person.getName().toString() + " " +
                        person.getSurname() + " : " + person.getPhoneNumber()
            )
            personsListTextView!!.append("\n")
        }
    }
}