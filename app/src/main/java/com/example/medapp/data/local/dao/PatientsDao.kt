package com.example.medapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.medapp.domain.models.Patient
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPatient(patient: Patient)
    @Query("SELECT * FROM patient ORDER BY id DESC")
    fun getAllProducts(): Flow<List<Patient>>
    @Update
    suspend fun updatePatient(patient: Patient)
    @Delete
    suspend fun deletePatient(patient: Patient)
}