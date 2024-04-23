package com.example.medapp.presentation.common

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
import androidx.compose.ui.unit.dp
import com.example.medapp.domain.models.Drug
import com.example.medapp.domain.models.Patient

@Composable
fun DrugInfo(drug: Drug){
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
        ,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ){
        Column (
            modifier = Modifier.padding(16.dp)
        ){
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
    }
}