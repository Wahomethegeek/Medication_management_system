package com.example.medapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Patient")
data class Patient(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String,
    val birthDate: String,
    val phoneNo: String,
    val email: String
)