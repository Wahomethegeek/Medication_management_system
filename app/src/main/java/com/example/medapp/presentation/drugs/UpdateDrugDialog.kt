package com.example.medapp.presentation.drugs

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.medapp.domain.models.Drug
import com.example.medapp.presentation.common.AppTextField
import com.example.medapp.utils.Utils
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateDrugDialog(
    drug: Drug,
    onclickUpdate: (Drug) -> Unit,
    onDismiss: (Drug) -> Unit
) {
    var name by remember { mutableStateOf(drug.name) }
    var expiryDate by remember { mutableStateOf(drug.expiryDate) }
    var quantity by remember { mutableStateOf(drug.quantity.toString()) }

    var showDatePicker by remember { mutableStateOf(false) }
    val dateOfBirthMillis = runCatching {
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(Utils.getDateOfBirth())?.time
            ?: 0L
    }.getOrDefault(0L)

    val selectedDateOfBirthMillis = runCatching {
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(expiryDate)?.time
            ?: 0L
    }.getOrDefault(0L)

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = selectedDateOfBirthMillis,
    )
    val selectedTimestamp = remember { mutableLongStateOf(dateOfBirthMillis) }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
            },
            dismissButton = {
                TextButton(onClick = {
                    showDatePicker = false
                }) {
                    Text(text = "Cancel")
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        selectedTimestamp.longValue =
                            datePickerState.selectedDateMillis ?: dateOfBirthMillis
                        expiryDate = Utils.convertMillisToDate(selectedTimestamp.longValue)
                        showDatePicker = false
                    }
                ) {
                    Text(text = "OK")
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
            )
        }
    }


    AlertDialog(onDismissRequest = {
        onDismiss.invoke(drug)
    },
        title = {
            Text(
                drug.name,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
            )
        },
        text = {
            LazyColumn(
                modifier = Modifier.padding(10.dp)
            ) {
                item {
                    AppTextField(
                        label = "Name",
                        value = name,
                        placeholder = "Enter drug name",
                        error = null,
                        onValueChanged = { patientName -> name = patientName },
                        keyboardType = KeyboardType.Text
                    )
                }
                item {
                    AppTextField(
                        label = "Quantity",
                        value = quantity,
                        placeholder = "Enter Drug Quantity",
                        error = null,
                        onValueChanged = { qty -> quantity = qty },
                        keyboardType = KeyboardType.Number
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Expiry date")
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                            .height(50.dp)
                            .clip(
                                RoundedCornerShape(10.dp)
                            )
                            .border(
                                1.dp,
                                MaterialTheme.colorScheme.onBackground,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .clickable {
                                showDatePicker = true
                            },
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = expiryDate,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }

            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss.invoke(drug) }) {
                Text(text = "Cancel")
            }

        },
        confirmButton = {
            TextButton(onClick = {
                onclickUpdate.invoke(
                    drug.copy(
                        name = name,
                        expiryDate = expiryDate,
                        quantity = quantity.toLong()
                    ),
                )

            }) {
                Text(text = "Update")
            }

        })
}