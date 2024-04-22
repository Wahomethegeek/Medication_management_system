package com.example.medapp.data.local.repository

import com.example.medapp.data.local.entities.MedicationEntity
import com.example.medapp.data.local.dao.MedicationRecordDao

class MedicationRecordRepository(private val dao: MedicationRecordDao)  {
    suspend fun addMedicationRecord(medicationRecord: MedicationEntity){
        dao.addMedicationRecord(medicationRecord)
    }
}