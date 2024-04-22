package com.example.medapp.data.local.dao

import com.example.medapp.data.local.entities.MedicationEntity

/*

class MedicationRecordDaoImpl(private val appDatabase: AppDatabase) : MedicationRecordDao() {
    private val medicationRecordDao = appDatabase.medicationRecordDao()

    override suspend fun addMedicationRecord(medicationRecord: MedicationEntity){
        medicationRecordDao.addMedicationRecord(medicationRecord)
    }
}*/
class MedicationRecordDaoImpl(private val medicationRecordDao: MedicationRecordDao) {
    suspend fun addMedicationRecord(medicationRecord: MedicationEntity) {
        medicationRecordDao.addMedicationRecord(medicationRecord)
    }
}
