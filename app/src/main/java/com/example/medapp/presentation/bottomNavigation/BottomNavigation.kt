package com.example.medapp.presentation.bottomNavigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.medapp.domain.models.BottomNavigationItem
import com.example.medapp.presentation.drugs.DrugsPage
import com.example.medapp.presentation.prescription.PrescriptionPage
import com.example.medapp.presentation.patients.PatientsPage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigation(navController: NavController){
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    Scaffold(
        bottomBar = {
            NavigationBar(
                contentColor  = MaterialTheme.colorScheme.onBackground,
                containerColor = MaterialTheme.colorScheme.background
            ) {
                BottomNavigationItem.bottomNavigationItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                        },
                        label = {
                            Text(
                                text = item.title, color =  MaterialTheme.colorScheme.onBackground

                            )
                        },
                        alwaysShowLabel = true,
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (item.badgeCount != null) {
                                        Badge {
                                            Text(text = item.badgeCount.toString())

                                        }
                                    } else if (item.hasNews) {
                                        Badge()
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title,
                                    tint = if (selectedItemIndex == index) {
                                        MaterialTheme.colorScheme.onBackground
                                    } else {
                                        Color.Gray
                                    }
                                )
                            }
                        }
                    )
                }
            }
        }
    ) {paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {

            when (selectedItemIndex) {
                0 -> {
                    PrescriptionPage(navController = navController)
                }

                1 -> {
                    PatientsPage(navController = navController)
                }

                2 -> {
                    DrugsPage(navController = navController)
                }

            }
        }

    }
}