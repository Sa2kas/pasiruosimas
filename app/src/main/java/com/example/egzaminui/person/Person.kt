package com.example.egzaminui.person

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Person {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
        set(name) {
            field = id
        }

    @ColumnInfo(name = "first_name")
    var name: String? = null

    @ColumnInfo(name = "last_name")
    var surname: String? = null

    @ColumnInfo(name = "phone_number")
    var phoneNumber: String? = null
}