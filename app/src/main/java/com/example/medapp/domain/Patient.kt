package com.example.medapp.domain

data class Patient(
    val patientId: Int,
    val name: String,
    val birthDate: String,
    val phoneNo: String,
    val email: String
)