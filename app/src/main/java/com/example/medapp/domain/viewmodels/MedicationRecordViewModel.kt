package com.example.medapp.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medapp.data.AppDatabase
import com.example.medapp.domain.models.Drug
import com.example.medapp.domain.models.MedicationRecord
import com.example.medapp.utils.ResultStatus
import com.example.medapp.utils.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicationRecordViewModel @Inject constructor(private val database: AppDatabase) : ViewModel() {

    val medicationState = MutableStateFlow(
        Results<List<MedicationRecord>>(
            data = null,
            message = null,
            status = ResultStatus.INITIAL
        )
    )

    init {
        getMedicationRecord()
    }

     fun getMedicationRecord() {
        viewModelScope.launch {
            medicationState.value = Results.loading()
            database.medicationRecordDao().getAllRecords().catch {
                medicationState.value = Results.error(it.message.toString())
            }.collect{
                medicationState.value = Results.success(it)
            }
        }
    }

    fun searchMedicationRecord(searchTerm: String) {
        viewModelScope.launch {
            database.medicationRecordDao().searchRecord(searchTerm).catch {
                medicationState.value = Results.error(it.message.toString())
            }.collect{
                medicationState.value = Results.success(it)
            }
        }
    }

    fun addMedicationRecord(medicationRecord: MedicationRecord ){
        viewModelScope.launch {
            database.medicationRecordDao().addMedicationRecord(medicationRecord)
        }
    }
    fun updateMedicationRecord(medicationRecord: MedicationRecord ){
        viewModelScope.launch {
            database.medicationRecordDao().updateRecord(medicationRecord)
        }
    }

    fun deleteMedication(medicationRecord: MedicationRecord){
        viewModelScope.launch {
            database.medicationRecordDao().deleteRecord(medicationRecord)
        }
    }
}

