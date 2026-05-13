package com.satyayudha0077.assessment_mobpro.navigation

const val KEY_ID_BUAH = "idBuah"

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object About: Screen("aboutScreen")
    data object FormBaru: Screen("detailScreen")

    data object FormUbah: Screen("detailScreen/{$KEY_ID_BUAH}") {
        fun withId(id: Long) = "detailScreen/$id"
    }

    data object RecycleBin : Screen("recycle_bin")
}