package com.example.medapp.domain.models

data class Patient(
    val patientId: Int,
    val name: String,
    val birthDate: String,
    val phoneNo: String,
    val email: String
)