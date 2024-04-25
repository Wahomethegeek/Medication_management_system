package com.example.medapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.medapp.domain.models.MedicationRecord
import com.example.medapp.domain.models.PatientMedicationInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicationRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMedicationRecord(medicationRecord: MedicationRecord)

    @Query("SELECT * FROM medication_records ORDER BY id DESC")
    fun getAllRecords(): Flow<List<MedicationRecord>>

    @Query("SELECT * FROM medication_records WHERE dosage LIKE '%' || :searchTerm || '%' OR drugName LIKE '%' || :searchTerm || '%' OR dosage LIKE '%' || :searchTerm || '%' OR patientId LIKE '%' || :searchTerm || '%' ORDER BY id DESC")
    fun searchRecord(searchTerm : String): Flow<List<MedicationRecord>>

    @Query("""
        SELECT p.name, p.birthDate, p.phoneNo, p.email, 
               m.dosage, m.instructions, m.prescriptionDate, m.status, 
               d.name AS drugName, d.expiryDate
        FROM Patient p
        JOIN medication_records m ON p.id = m.patientId
        JOIN Drugs d ON m.drugId = d.id
        WHERE p.id = :patientId
    """)
    fun getPatientMedications(patientId: Long): Flow<List<PatientMedicationInfo>>
    @Update
    suspend fun updateRecord(record: MedicationRecord)
    @Delete
    suspend fun deleteRecord(record: MedicationRecord)
}
