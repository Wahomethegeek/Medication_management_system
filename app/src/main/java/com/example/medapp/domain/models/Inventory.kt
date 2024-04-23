package com.example.medapp.domain.models

data class Inventory(
    val medicationId: Int,
    val stockLevels: Int,
    val expDate: String,
    val replenishmentNeeds: Int
)
