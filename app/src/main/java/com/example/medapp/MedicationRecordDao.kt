package com.example.medapp

import androidx.room.Dao
import androidx.room.Insert

@Dao
/*
open class MedicationRecordDao {
    @Insert
    open suspend fun addMedicationRecord(medicationRecord: MedicationEntity) {
    }
}*/
interface MedicationRecordDao {
    @Insert
    suspend fun addMedicationRecord(medicationRecord: MedicationEntity)
}
