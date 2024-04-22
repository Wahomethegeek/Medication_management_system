package com.example.medapp

class MedicationRecordRepository(private val dao: MedicationRecordDao) {
    suspend fun addMedicationRecord(medicationRecord: MedicationEntity){
        dao.addMedicationRecord(medicationRecord)
    }
}