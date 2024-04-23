package com.example.medapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.medapp.data.local.entities.MedicationEntity

@Dao
interface MedicationRecordDao {
    @Insert
    suspend fun addMedicationRecord(medicationRecord: MedicationEntity)
}
