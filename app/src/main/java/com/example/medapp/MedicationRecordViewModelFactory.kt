package com.example.medapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MedicationRecordViewModelFactory(private val repository: MedicationRecordRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MedicationRecordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MedicationRecordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
