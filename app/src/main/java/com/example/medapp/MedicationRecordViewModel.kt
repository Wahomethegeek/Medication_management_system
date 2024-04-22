package com.example.medapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MedicationRecordViewModel(private val repository: MedicationRecordRepository) : ViewModel() {

    private val _addMedicationRecordResult = MutableLiveData<Boolean>()
    val addMedicationRecordResult: LiveData<Boolean>
        get() = _addMedicationRecordResult

    // CRUD using repository methods
    fun addMedicationRecord(medicationRecord: MedicationEntity){
        viewModelScope.launch {
            try {
                repository.addMedicationRecord(medicationRecord)
                _addMedicationRecordResult.value = true // If success set to true
            } catch (e: Exception){
                _addMedicationRecordResult.value = false // set to false if there is an error
            }

        }

    }

    // other crud operations


}

