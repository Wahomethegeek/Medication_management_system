package com.example.medapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Drugs")
data class Drug(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val name: String,
    val quantity: Long,
    val expiryDate: String,
    val initialQuantity: Long
)
