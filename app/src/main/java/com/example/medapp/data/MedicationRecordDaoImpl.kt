package com.example.medapp.data

import com.example.medapp.data.MedicationEntity
import com.example.medapp.data.MedicationRecordDao

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
