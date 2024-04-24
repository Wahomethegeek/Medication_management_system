package com.example.medapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.medapp.domain.models.MedicationRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicationRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMedicationRecord(medicationRecord: MedicationRecord)

    @Query("SELECT * FROM medication_records ORDER BY id DESC")
    fun getAllRecords(): Flow<List<MedicationRecord>>
    @Update
    suspend fun updateRecord(record: MedicationRecord)
    @Delete
    suspend fun deleteRecord(record: MedicationRecord)
}
