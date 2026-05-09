package com.satyayudha0077.assessment_mobpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.satyayudha0077.assessment_mobpro.ui.screen.MainScreen
import com.satyayudha0077.assessment_mobpro.ui.theme.Assessment_mobproTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assessment_mobproTheme {
                MainScreen()
            }
        }
    }
}