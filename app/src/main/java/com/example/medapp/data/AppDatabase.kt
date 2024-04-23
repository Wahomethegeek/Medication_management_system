package com.example.medapp.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.medapp.data.local.dao.DrugsDao
import com.example.medapp.data.local.entities.MedicationEntity
import com.example.medapp.data.local.dao.MedicationRecordDao
import com.example.medapp.data.local.dao.PatientsDao
import com.example.medapp.domain.models.Drug
import com.example.medapp.domain.models.Patient

@Database(entities = [MedicationEntity::class, Patient::class, Drug::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun medicationRecordDao(): MedicationRecordDao
    abstract  fun patientRecordDao(): PatientsDao
    abstract  fun drugsRecordDao(): DrugsDao
}