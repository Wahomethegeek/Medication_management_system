package com.example.medapp.presentation.patients

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medapp.domain.viewmodels.PatientsViewModel
import com.example.medapp.presentation.navigation.Screen
import com.example.medapp.utils.ResultStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientDosagePage(navController: NavController, patientName: String, patientId: Long) {
    val patientsViewModel = hiltViewModel<PatientsViewModel>()
    val patientsDosageState = patientsViewModel.patientsDosageState.collectAsState().value
    LaunchedEffect(true) {
        patientsViewModel.getPatientDosage(patientId)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBackIos,
                        contentDescription = "Back"
                    )
                }
            }, title = { Text(text = patientName) })
        },
    ) { paddingValues ->
        when(patientsDosageState.status){
            ResultStatus.INITIAL, ResultStatus.LOADING -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            ResultStatus.SUCCESS -> {

                if(patientsDosageState.data.isNullOrEmpty()){
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues), contentAlignment = Alignment.Center
                    ) {
                        Text(text = "$patientName is not on any medication")
                    }
                }else{
                    LazyColumn(modifier = Modifier.padding(paddingValues)) {
                        items(patientsDosageState.data){medInfo ->
                            PatientMedicationCard(medInfo)
                        }
                    }

                }

            }
            ResultStatus.ERROR -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues), contentAlignment = Alignment.Center
                ) {
                    Text(text = patientsDosageState.message.toString())
                }
            }

        }


    }
}