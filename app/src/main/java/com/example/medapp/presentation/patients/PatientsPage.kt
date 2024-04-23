package com.example.medapp.presentation.patients

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medapp.domain.viewmodels.PatientsViewModel
import com.example.medapp.presentation.PatientInfo
import com.example.medapp.presentation.navigation.Screen
import com.example.medapp.utils.ResultStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientsPage(navController: NavController) {
    val patientsViewModel = hiltViewModel<PatientsViewModel>()
    val patientsState = patientsViewModel.patientsState.collectAsState().value
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "Patients") })
        },
        floatingActionButton = {
            FloatingActionButton(shape = CircleShape, onClick = { navController.navigate(Screen.AddPatientScreen.route) }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Patient")
            }
        }
    ) { paddingValues ->
        
        when(patientsState.status){
            ResultStatus.INITIAL , ResultStatus.LOADING ->  {
                Box(modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            ResultStatus.SUCCESS -> {
                if(patientsState.data.isNullOrEmpty()){
                    Box(modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Use the + icon to add a patient")
                    }
                }else{
                    LazyColumn(modifier = Modifier.padding(paddingValues)) {
                       items( patientsState.data){patient ->
                            PatientInfo(patient = patient)
                        }
                    }
                }
            }
            ResultStatus.ERROR -> {
                Box(modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = patientsState.message.toString())
                }
            }
            
        }
    }
}