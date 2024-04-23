package com.example.medapp.domain.models

data class MedicationRecord(
    val medicationId: Int,
    val patientId: Int,
    val drugName: String,
    val dosage: String,
    val instructions: String,
    val prescriptionDate: String,
    val status: String
)
