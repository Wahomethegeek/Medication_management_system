package com.example.medapp

data class Prescription(
    val prescriptionId: Int,
    val medicationId: Int,
    val patientId: Int,
    val physicianName: String,
    val prescriptionDate: String,
    val status: String
)