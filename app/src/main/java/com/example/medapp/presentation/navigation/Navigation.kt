package com.example.medapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medapp.presentation.bottomNavigation.BottomNavigation
import com.example.medapp.presentation.drugs.AddDrugsPage
import com.example.medapp.presentation.drugs.DrugsPage
import com.example.medapp.presentation.medication.PrescriptionPage
import com.example.medapp.presentation.patients.AddPatientsPage
import com.example.medapp.presentation.patients.PatientsPage
import com.example.medapp.presentation.medication.AddPrescriptionPage

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.BottomNavigationScreen.route,
    ) {
        composable(
            route = Screen.BottomNavigationScreen.route
        ) {
            BottomNavigation(navController = navController)
        }

        composable(
            route = Screen.PrescriptionScreen.route
        ) {
            PrescriptionPage(navController = navController)
        }
        composable(
            route = Screen.AddPrescriptionScreen.route
        ) {
            AddPrescriptionPage(navController = navController)
        }
        composable(
            route = Screen.PatientScreen.route
        ) {
            PatientsPage(navController = navController)
        }

        composable(
            route = Screen.AddPatientScreen.route
        ) {
            AddPatientsPage(navController = navController)
        }

        composable(
            route = Screen.DrugsScreen.route
        ) {
            DrugsPage(navController = navController)
        }

        composable(
            route = Screen.AddDrugsScreen.route
        ) {
            AddDrugsPage(navController = navController)
        }
    }

}