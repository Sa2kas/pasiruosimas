package com.example.egzaminui.person

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_table")
class Person (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val  surname: String,
    val phoneNumber: String
)