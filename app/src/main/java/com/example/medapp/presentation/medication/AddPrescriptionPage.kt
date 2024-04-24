package com.example.medapp.presentation.medication

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medapp.domain.models.Drug
import com.example.medapp.domain.models.MedicationRecord
import com.example.medapp.domain.models.Patient
import com.example.medapp.domain.models.TextFieldState
import com.example.medapp.domain.viewmodels.DrugsViewModel
import com.example.medapp.domain.viewmodels.MedicationRecordViewModel
import com.example.medapp.domain.viewmodels.PatientsViewModel
import com.example.medapp.presentation.common.AppButton
import com.example.medapp.presentation.common.AppDropDown
import com.example.medapp.presentation.common.AppTextField
import com.example.medapp.utils.Utils
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPrescriptionPage(navController: NavController){
    val context = LocalContext.current

    val medicationViewModel = hiltViewModel<MedicationRecordViewModel>()
    val patientsViewModel = hiltViewModel<PatientsViewModel>()
    val drugsViewModel = hiltViewModel<DrugsViewModel>()
    val patientsState = patientsViewModel.patientsState.collectAsState().value
    val drugsState = drugsViewModel.drugsState.collectAsState().value

    val selectedPatient = remember {
        mutableStateOf<Patient?>(null)
    }
    val selectedDrug = remember {
        mutableStateOf<Drug?>(null)
    }

    var dosage by remember { mutableStateOf("") }
    var prescriptionDate by remember { mutableStateOf(Utils.getDateOfBirth()) }
    var instructions by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    val dateOfBirthMillis = runCatching {
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(Utils.getDateOfBirth())?.time
            ?: 0L
    }.getOrDefault(0L)

    val selectedDateOfBirthMillis = runCatching {
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(prescriptionDate)?.time
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
                        prescriptionDate = Utils.convertMillisToDate(selectedTimestamp.longValue)
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
    Scaffold(

        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Back")
                    }
                },
                title = { Text(text = "Add Prescription") })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 10.dp)
        ) {
            patientsState.data?.let {
                item {
                    Text(text = "Patient")
                    AppDropDown(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        options = it,
                        selectedOption = TextFieldState(selectedPatient.value?.name ?: "Select a patient"),
                        onOptionSelected = { patient ->
                            selectedPatient.value = patient
                        },
                        textStyle = MaterialTheme.typography.titleSmall.copy(
                            fontSize = 16.sp
                        ),
                        content = { patient ->
                            Text(
                                text = patient.name,
                                style = MaterialTheme.typography.labelLarge
                            )
                        },
                    )
                }
            }


            drugsState.data?.let {
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Prescription date")
                    AppDropDown(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        options = it,
                        selectedOption = TextFieldState(selectedDrug.value?.name ?: "Select a drug"),
                        onOptionSelected = { drug ->
                            selectedDrug.value = drug
                        },
                        textStyle = MaterialTheme.typography.titleSmall.copy(
                            fontSize = 16.sp
                        ),
                        content = { drug ->
                            Text(
                                text = drug.name,
                                style = MaterialTheme.typography.labelLarge
                            )
                        },
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            item {
                AppTextField(
                    label = "Dosage",
                    value = dosage,
                    placeholder = "Enter dosage for this medicine",
                    error = null,
                    onValueChanged = { patientName -> dosage = patientName },
                    keyboardType = KeyboardType.Text
                )
            }
            item {
                AppTextField(
                    label = "Instructions",
                    value = instructions,
                    placeholder = "Enter instructions",
                    error = null,
                    onValueChanged = { patientEmail -> instructions = patientEmail },
                    keyboardType = KeyboardType.Email
                )
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Prescription date")
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
                        text = prescriptionDate,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            item {
                AppButton({
                    if (selectedPatient.value == null){
                        Toast.makeText(context, "Please select a patient first", Toast.LENGTH_SHORT).show()
                    }else if(selectedDrug.value ==null){
                        Toast.makeText(context, "Please select a drug first", Toast.LENGTH_SHORT).show()
                    } else{
                        medicationViewModel.addMedicationRecord(
                            MedicationRecord(
                                id = null,
                                patientId = selectedPatient.value!!.id!!.toLong(),
                                dosage = dosage,
                                status = "Active",
                                drugName = selectedDrug.value!!.name,
                                instructions = instructions,
                                prescriptionDate = prescriptionDate
                            )
                        )
                        navController.navigateUp()
                        Toast.makeText(context, "Medical record added successfully", Toast.LENGTH_SHORT).show()
                    }

                }, { Text("Add Record") })
            }
        }
    }
}