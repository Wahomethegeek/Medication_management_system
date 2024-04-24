package com.example.medapp.presentation.prescription

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medapp.domain.models.MedicationRecord

@Composable
fun MedicationRecordItem(medicationRecord: MedicationRecord, onDelete: (MedicationRecord) -> Unit){
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ){
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    text = "Medication ID: ${medicationRecord.id}",
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Patient ID: ${medicationRecord.patientId}",
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Drug Name: ${medicationRecord.drugName}",
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Dosage: ${medicationRecord.dosage}",
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Instructions: ${medicationRecord.instructions}",
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Prescription Date: ${medicationRecord.prescriptionDate}",
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Status: ${medicationRecord.status}",
                )
            }
            IconButton(modifier = Modifier.align(Alignment.BottomEnd), onClick = { onDelete.invoke(medicationRecord)}) {
              Icon(imageVector = Icons.Filled.Delete, contentDescription ="Delete", tint = MaterialTheme.colorScheme.error )
            }
            IconButton(modifier = Modifier.align(Alignment.TopEnd), onClick = { }) {
                Icon(imageVector = Icons.Filled.Edit, contentDescription ="Edit" )
            }
        }
    }
}
@Preview
@Composable
fun PreviewMedicationItem(){
    val medicationRecord = MedicationRecord(
        id = 1,
        patientId = 123,
        drugName = "Panadols",
        dosage = "10 mg",
        instructions = "Take twice daily",
        prescriptionDate = "2024-04-22",
        status = "Active"
        
    )
    MedicationRecordItem(medicationRecord = medicationRecord, onDelete = {})
}