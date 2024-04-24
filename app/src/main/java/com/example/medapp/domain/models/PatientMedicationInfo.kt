package com.example.medapp.domain.models

data class PatientMedicationInfo(
    val name: String,
    val birthDate: String,
    val phoneNo: String,
    val email: String,
    val dosage: String,
    val instructions: String,
    val prescriptionDate: String,
    val status: String,
    val drugName: String,
    val expiryDate: String
)
