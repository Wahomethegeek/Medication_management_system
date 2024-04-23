package com.example.medapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.medapp.domain.models.Drug
import kotlinx.coroutines.flow.Flow

@Dao
interface DrugsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrug(drug: Drug)
    @Query("SELECT * FROM Drugs ORDER BY id DESC")
    fun getAllDrugs(): Flow<List<Drug>>
    @Update
    suspend fun updateDrug(drug:Drug)
    @Delete
    suspend fun deleteDrug(drug: Drug)
}