package com.example.medapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medapp.domain.models.Patient
import com.example.medapp.ui.theme.OrangeColor6

@Composable
fun PatientInfo(patient: Patient, onDelete: (Patient) -> Unit){
    Card(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                ,
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ){
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Patient ID: ${patient.id}",
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Name: ${patient.name}",
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Date of birth: ${patient.birthDate}",
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Phone Number: ${patient.phoneNo}",
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Email: ${patient.email}",
                )
            }

            IconButton(modifier = Modifier.align(Alignment.TopEnd), onClick = { onDelete.invoke(patient)}) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription ="Delete", tint = MaterialTheme.colorScheme.error )
            }
        }
    }
}
@Preview
@Composable
fun PreviewPatientItem(){
    val patientInfo = Patient(
        id = 123,
        name = "Wahome",
        birthDate = "2000-08-23",
        phoneNo = "0719196535",
        email = "kenwahome99@gmail.com"
    )
    PatientInfo(patient = patientInfo, onDelete = {})
}