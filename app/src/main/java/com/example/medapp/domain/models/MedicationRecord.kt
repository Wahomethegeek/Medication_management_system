package com.example.medapp.domain.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "medication_records",
    foreignKeys = [
        ForeignKey(
            entity = Patient::class,
            parentColumns = ["id"],
            childColumns = ["patientId"],
            onDelete = ForeignKey.CASCADE
        ),
          ForeignKey(
            entity = Drug::class,
            parentColumns = ["id"],
            childColumns = ["drugId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MedicationRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val patientId: Long,
    val drugName: String,
    val drugId: Long,
    val dosage: String,
    val instructions: String,
    val prescriptionDate: String,
    val status: String
)
