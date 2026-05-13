package com.satyayudha0077.assessment_mobpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.satyayudha0077.assessment_mobpro.database.BuahDb
import com.satyayudha0077.assessment_mobpro.model.Buah
import kotlinx.coroutines.launch
import com.satyayudha0077.assessment_mobpro.navigation.SetupNavGraph
import com.satyayudha0077.assessment_mobpro.ui.theme.Assessment_mobproTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val dao = BuahDb.getInstance(this).dao

        lifecycleScope.launch {
            if (dao.getBuahById(1) == null) {

                dao.insert(
                    Buah(
                        nama = "Apel",
                        manfaat = "Baik untuk kesehatan jantung",
                        imageResId = R.drawable.apel
                    )
                )

                dao.insert(
                    Buah(
                        nama = "Jeruk",
                        manfaat = "Mengandung vitamin C",
                        imageResId = R.drawable.jeruk
                    )
                )

                dao.insert(
                    Buah(
                        nama = "Pisang",
                        manfaat = "Sumber energi alami",
                        imageResId = R.drawable.pisang
                    )
                )
            }
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assessment_mobproTheme {
                SetupNavGraph()
            }
        }
    }
}