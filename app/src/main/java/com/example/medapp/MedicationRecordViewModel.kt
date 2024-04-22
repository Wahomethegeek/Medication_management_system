package com.example.medapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MedicationRecordViewModel(private val repository: MedicationRecordRepository) : ViewModel() {

    // CRUD using repository methods
    fun addMedicationRecord(medicationRecord: MedicationEntity){
        viewModelScope.launch {
            repository.addMedicationRecord(medicationRecord)
        }

    }

    // other crud operations


}

