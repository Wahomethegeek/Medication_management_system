package com.example.medapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.medapp.data.local.entities.MedicationEntity
import com.example.medapp.data.local.dao.MedicationRecordDao

@Database(entities = [MedicationEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun medicationRecordDao(): MedicationRecordDao

//    companion object {
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        fun getInstance(context: Context): AppDatabase {
//            return INSTANCE ?: synchronized(this){
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "MedicationRecords.db"
//                ).createFromAsset("MedicationRecords.db")
//                    .fallbackToDestructiveMigration()
//                    .build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
}