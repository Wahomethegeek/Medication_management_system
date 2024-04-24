package com.example.medapp.presentation.patients

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
import androidx.compose.material3.SelectableDates
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.medapp.domain.models.Patient
import com.example.medapp.domain.viewmodels.PatientsViewModel
import com.example.medapp.presentation.common.AppButton
import com.example.medapp.presentation.common.AppTextField
import com.example.medapp.utils.Utils
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePatientDialog(
    patient: Patient,
    onclickUpdate: (Patient) -> Unit,
    onDismiss: (Patient) -> Unit
){
    var name by remember { mutableStateOf(patient.name) }
    var birthDate by remember { mutableStateOf(patient.birthDate) }
    var phoneNo by remember { mutableStateOf(patient.phoneNo) }
    var email by remember { mutableStateOf(patient.email) }
    var showDatePicker by remember { mutableStateOf(false) }
    val dateOfBirthMillis = runCatching {
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(Utils.getDateOfBirth())?.time
            ?: 0L
    }.getOrDefault(0L)

    val selectedDateOfBirthMillis = runCatching {
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(birthDate)?.time
            ?: 0L
    }.getOrDefault(0L)

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = selectedDateOfBirthMillis,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis <= dateOfBirthMillis

            }
        }
    )
    val selectedTimestamp = remember { mutableLongStateOf(dateOfBirthMillis) }


    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
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
                        birthDate = Utils.convertMillisToDate(selectedTimestamp.longValue)
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
        onDismiss.invoke(patient)
    },
        title = {
            Text(
                patient.name,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
            )
        },
        text = {
            LazyColumn(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                item {
                    AppTextField(
                        label = "Name",
                        value = name,
                        placeholder = "Enter patient name",
                        error = null,
                        onValueChanged = { patientName -> name = patientName },
                        keyboardType = KeyboardType.Text
                    )
                }
                item {
                    AppTextField(
                        label = "Email",
                        value = email,
                        placeholder = "Enter patient email",
                        error = null,
                        onValueChanged = { patientEmail -> email = patientEmail },
                        keyboardType = KeyboardType.Email
                    )
                }
                item {
                    AppTextField(
                        label = "Phone Number",
                        value = phoneNo,
                        placeholder = "Enter patient phone number",
                        error = null,
                        onValueChanged = { phoneNumber -> phoneNo = phoneNumber },
                        keyboardType = KeyboardType.Phone
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Date of Birth")
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
                            text = birthDate,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }


            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss.invoke(patient) }) {
                Text(text = "Cancel")
            }

        },
        confirmButton = {
            TextButton(onClick = {

                onclickUpdate.invoke(
                    patient.copy(
                        name = name,
                        birthDate = birthDate,
                        email = email,
                        phoneNo = phoneNo
                    ),

                )
            }) {
                Text(text = "Update")
            }

        })

}