package com.example.medapp.presentation.drugs

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medapp.domain.models.Drug
import com.example.medapp.domain.viewmodels.DrugsViewModel
import com.example.medapp.presentation.common.DrugInfo
import com.example.medapp.presentation.navigation.Screen
import com.example.medapp.utils.ResultStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrugsPage(navController: NavController) {
    val drugsViewModel = hiltViewModel<DrugsViewModel>()
    val drugsState = drugsViewModel.drugsState.collectAsState().value
    val context = LocalContext.current
    var selectedDrug by remember { mutableStateOf<Drug?>(null) }

        selectedDrug?.let { dr ->
            UpdateDrugDialog(drug = dr, onclickUpdate = { drug ->
                drugsViewModel.updateDrug(drug)
                selectedDrug = null
                Toast.makeText(context, "Drug updated successfully", Toast.LENGTH_SHORT)
                    .show()
            }, onDismiss = {
                selectedDrug = null
            })
        }



    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "Drugs") })
        },
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = { navController.navigate(Screen.AddDrugsScreen.route) }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add drug")
            }
        }
    ) { paddingValues ->


        when (drugsState.status) {
            ResultStatus.INITIAL, ResultStatus.LOADING -> {
                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            ResultStatus.SUCCESS -> {
                if (drugsState.data.isNullOrEmpty()) {
                    Box(
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Use the + icon to add a drug")
                    }
                } else {
                    LazyColumn(modifier = Modifier.padding(paddingValues)) {
                        items(drugsState.data) { drug ->
                            DrugInfo(drug = drug,
                                onEdit = { dr ->
                                    selectedDrug = dr
                                },
                                onDelete = { dr ->
                                    drugsViewModel.deleteDrug(dr)
                                    Toast.makeText(
                                        context,
                                        "Drug deleted successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                })
                        }
                    }
                }
            }

            ResultStatus.ERROR -> {
                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(text = drugsState.message.toString())
                }
            }

        }
    }
}