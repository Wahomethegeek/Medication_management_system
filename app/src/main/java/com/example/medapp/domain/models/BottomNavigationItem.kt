package com.example.medapp.domain.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.automirrored.outlined.Assignment
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MedicalInformation
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MedicalInformation
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
){
    companion object{
        val bottomNavigationItems = listOf(
            BottomNavigationItem(
                title = "Medications",
                selectedIcon = Icons.AutoMirrored.Filled.Assignment,
                unselectedIcon = Icons.AutoMirrored.Outlined.Assignment,
                hasNews = false,
            ),

            BottomNavigationItem(
                title = "Patients",
                selectedIcon = Icons.Filled.Person,
                unselectedIcon = Icons.Outlined.Person,
                hasNews = false,
            ),

            BottomNavigationItem(
                title = "Drugs",
                selectedIcon = Icons.Filled.MedicalInformation,
                unselectedIcon = Icons.Outlined.MedicalInformation,
                hasNews = false,
            ),
        )
    }
}
