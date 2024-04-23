package com.example.medapp.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medapp.data.AppDatabase
import com.example.medapp.domain.models.Drug
import com.example.medapp.domain.models.Patient
import com.example.medapp.utils.ResultStatus
import com.example.medapp.utils.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrugsViewModel @Inject constructor(private  val database: AppDatabase): ViewModel() {
    val drugsState = MutableStateFlow(
        Results<List<Drug>>(
            data = null,
            message = null,
            status = ResultStatus.INITIAL
        )
    )

    init {
        getDrugs()
    }

    private fun getDrugs() {
        viewModelScope.launch {
            drugsState.value = Results.loading()
            database.drugsRecordDao().getAllDrugs().catch {
                drugsState.value = Results.error(it.message.toString())
            }.collect{
                drugsState.value = Results.success(it)
            }
        }
    }

    fun insertDrug(drug: Drug){
        viewModelScope.launch {
            database.drugsRecordDao().insertDrug(drug)
        }
    }
}