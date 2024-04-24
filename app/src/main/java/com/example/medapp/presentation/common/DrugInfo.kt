package com.example.medapp.presentation.common

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
import com.example.medapp.domain.models.Drug
import com.example.medapp.domain.models.Patient

@Composable
fun DrugInfo(drug: Drug, onDelete: (Drug) -> Unit){
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
                    text = "Drug ID: ${drug.id}",
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Name: ${drug.name}",
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Expiry date: ${drug.expiryDate}",
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Stock: ${drug.quantity}",
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Initial Quantity: ${drug.initialQuantity}",
                )
            }

            IconButton(modifier = Modifier.align(Alignment.BottomEnd), onClick = { onDelete.invoke(drug)}) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription ="Delete", tint = MaterialTheme.colorScheme.error )
            }
            IconButton(modifier = Modifier.align(Alignment.TopEnd), onClick = { }) {
                Icon(imageVector = Icons.Filled.Edit, contentDescription ="Edit" )
            }
        }
    }
}