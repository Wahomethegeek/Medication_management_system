package com.example.medapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medapp.domain.models.MedicationRecord
import com.example.medapp.ui.theme.blueColor1

@Composable
fun MedicationRecordItem(medicationRecord: MedicationRecord){
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(color = blueColor1),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ){
        Column (
            modifier = Modifier.padding(16.dp)
        ){
            Text(
                text = "Medication ID: ${medicationRecord.medicationId}",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Patient ID: ${medicationRecord.patientId}",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Drug Name: ${medicationRecord.drugName}",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Dosage: ${medicationRecord.dosage}",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Instructions: ${medicationRecord.instructions}",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Prescription Date: ${medicationRecord.prescriptionDate}",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Status: ${medicationRecord.status}",
                color = Color.White
            )
        }
    }
}
@Preview
@Composable
fun PreviewMedicationItem(){
    val medicationRecord = MedicationRecord(
        medicationId = 1,
        patientId = 123,
        drugName = "Panadols",
        dosage = "10 mg",
        instructions = "Take twice daily",
        prescriptionDate = "2024-04-22",
        status = "Active"
        
    )
    MedicationRecordItem(medicationRecord = medicationRecord)
}