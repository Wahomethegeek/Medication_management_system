package com.example.medapp

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Medication")
data class MedicationEntity(
    @PrimaryKey(autoGenerate = true)
    val medicationId: Long = 0,
    val patientId: Long,
    val drugName: String,
    val dosage: String,
    val administrationInstructions: String,
    val prescriptionDate: String,
    val status: String

)