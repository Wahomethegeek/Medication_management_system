package com.example.medapp.presentation.patients

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.medapp.domain.models.PatientMedicationInfo

@Composable
fun PatientMedicationCard(medicationInfo: PatientMedicationInfo) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ){
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Patient Name: ${medicationInfo.name}",
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Drug name: ${medicationInfo.drugName}",
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Dosage: ${medicationInfo.dosage}",
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Instructions: ${medicationInfo.instructions}",
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Prescription date: ${medicationInfo.prescriptionDate}",
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Drug expiry date: ${medicationInfo.expiryDate}",
                )

            }
        }
    }
}