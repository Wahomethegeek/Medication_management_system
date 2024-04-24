package com.example.medapp.data.local.repository

import com.example.medapp.data.local.dao.MedicationRecordDao
import com.example.medapp.domain.models.MedicationRecord

class MedicationRecordRepository(private val dao: MedicationRecordDao)  {
    suspend fun addMedicationRecord(medicationRecord: MedicationRecord){
        dao.addMedicationRecord(medicationRecord)
    }
}