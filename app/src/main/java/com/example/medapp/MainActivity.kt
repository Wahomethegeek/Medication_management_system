package com.example.medapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.medapp.presentation.navigation.Navigation
import com.example.medapp.ui.theme.MedAppTheme
import com.example.medapp.ui.theme.blueColor1
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = blueColor1),

                ) {
                    Navigation()
//                    val viewModel  = hiltViewModel<MedicationRecordViewModel>()
//                    Column(
//                        modifier = Modifier.padding(16.dp),
//                        verticalArrangement = Arrangement.spacedBy(16.dp)
//                    ) {
//                        // Display Medication Records
//                        MedicationRecordItem(
//                            medicationRecord = MedicationRecord(
//                                medicationId = 1,
//                                patientId = 123,
//                                drugName = "Cetrazine",
//                                dosage = "10 mg",
//                                instructions = "Take twice a day",
//                                prescriptionDate = "2024-04-2",
//                                status = "Active"
//                            )
//                        )
//                        // button to add medication
//                        Button(onClick = {
//                            val medicationRecord = MedicationEntity(
//                                // Populate medication data
//                                patientId = 123,
//                                drugName = "Cetrazine",
//                                dosage = "10 mg",
//                                administrationInstructions = "Take twice a day",
//                                prescriptionDate = "2024-04-22",
//                                status = "Active"
//                            )
//                            viewModel.addMedicationRecord(medicationRecord)
//                        }) {
//                            Text(text = "Add Medication Record")
//                        }
//
//                        // live data observation
//                        val addMedicationRecordResult by viewModel.addMedicationRecordResult.observeAsState(initial = false)
//
//                        addMedicationRecordResult?.let { result ->
//                            if (result) {
//                                // Medication added successfully
//                                // You can show a message or update UI accordingly
//                            } else {
//                                // Failed to add medication
//                                // You can show an error message or handle the failure case
//                            }
//                        }
//
//
//                    }
                }
            }
        }
    }
}