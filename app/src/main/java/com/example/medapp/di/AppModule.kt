package com.example.medapp.di

import android.content.Context
import androidx.room.Room
import com.example.medapp.data.AppDatabase
import com.example.medapp.data.MedicationRecordRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "Medical_database"
        ).build()
    }


    @Provides
    @Singleton
    fun provideMedicalRepository(appDatabase: AppDatabase): MedicationRecordRepository {
        return MedicationRecordRepository(appDatabase.medicationRecordDao())
    }
}