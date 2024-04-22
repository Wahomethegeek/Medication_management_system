package com.example.medapp.data

import com.example.medapp.data.MedicationEntity
import com.example.medapp.data.MedicationRecordDao

class MedicationRecordRepository(private val dao: MedicationRecordDao)  {
    suspend fun addMedicationRecord(medicationRecord: MedicationEntity){
        dao.addMedicationRecord(medicationRecord)
    }
}