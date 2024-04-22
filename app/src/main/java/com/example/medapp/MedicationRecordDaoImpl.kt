package com.example.medapp
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
