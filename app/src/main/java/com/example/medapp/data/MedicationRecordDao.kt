package com.example.medapp.data

import androidx.room.Dao
import androidx.room.Insert
import com.example.medapp.data.MedicationEntity

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
