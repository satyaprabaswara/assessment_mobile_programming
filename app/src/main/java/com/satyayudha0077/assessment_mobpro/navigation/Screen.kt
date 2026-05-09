package com.satyayudha0077.assessment_mobpro.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
}