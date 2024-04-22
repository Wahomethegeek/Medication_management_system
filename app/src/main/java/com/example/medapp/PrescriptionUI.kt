package com.example.medapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.medapp.Prescription
import com.example.medapp.ui.theme.OrangeColor6

@Composable
fun PrescriptionItem(prescription: Prescription){
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(color = OrangeColor6),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ){
        Column (
            modifier = Modifier.padding(16.dp)
        ){
            Text(
                text = "Prescription ID: ${prescription.patientId}",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Patient ID: ${prescription.patientId}",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Medication ID: ${prescription.medicationId}",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Physician Name: ${prescription.physicianName}",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Prescription Date: ${prescription.prescriptionDate}",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Status: ${prescription.status}",
                color = Color.White
            )
        }
    }
}