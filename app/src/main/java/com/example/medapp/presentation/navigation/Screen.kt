package com.example.medapp.presentation.navigation

sealed class Screen(val route:String) {



    object BottomNavigationScreen : Screen("bottom_navigation_screen")
    object PrescriptionScreen : Screen("prescriptions_screen")
    object AddPrescriptionScreen : Screen("add_prescription_screen")
    object PatientScreen : Screen("patients_screen")
    object PatientDosageScreen : Screen("patient_dosage_page")
    object AddPatientScreen : Screen("add_patient_screen")
    object DrugsScreen : Screen("drugs_screen")
    object AddDrugsScreen : Screen("add_drug_screen")

    fun withArgs(vararg args: String):String{
        return  buildString {
            append(route)
            args.forEach {arg ->
                append("""/$arg""")
            }
        }
    }
}