package com.example.medapp.presentation.prescription

import android.widget.Toast
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medapp.domain.viewmodels.MedicationRecordViewModel
import com.example.medapp.presentation.navigation.Screen
import com.example.medapp.utils.ResultStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrescriptionPage(navController: NavController){
    val medicationViewModel = hiltViewModel<MedicationRecordViewModel>()
    val medicationState = medicationViewModel.medicationState.collectAsState().value
    val context = LocalContext.current

    Scaffold(
      topBar = {
          CenterAlignedTopAppBar(title = { Text(text = "Medications") })
      },
      floatingActionButton = { FloatingActionButton( shape = CircleShape,onClick = { navController.navigate(Screen.AddPrescriptionScreen.route) }) {
          Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Medication")
      }}
  ) {paddingValues ->
      when(medicationState.status){
          ResultStatus.INITIAL , ResultStatus.LOADING ->  {
              Box(modifier = Modifier
                  .padding(paddingValues)
                  .fillMaxSize(), contentAlignment = Alignment.Center) {
                  CircularProgressIndicator()
              }
          }
          ResultStatus.SUCCESS -> {
              if(medicationState.data.isNullOrEmpty()){
                  Box(modifier = Modifier
                      .padding(paddingValues)
                      .fillMaxSize(), contentAlignment = Alignment.Center) {
                      Text(text = "Use the + icon to add a medication")
                  }
              }else{
                  LazyColumn(modifier = Modifier.padding(paddingValues)) {
                      items( medicationState.data){record ->
                          MedicationRecordItem(record, onDelete = {medication ->
                              Toast.makeText(context, "Medication deleted successfully", Toast.LENGTH_SHORT).show()
                              medicationViewModel.deleteMedication(medication)})
                      }
                  }
              }
          }
          ResultStatus.ERROR -> {
              Box(modifier = Modifier
                  .padding(paddingValues)
                  .fillMaxSize(), contentAlignment = Alignment.Center) {
                  Text(text = medicationState.message.toString())
              }
          }

      }
  }
}