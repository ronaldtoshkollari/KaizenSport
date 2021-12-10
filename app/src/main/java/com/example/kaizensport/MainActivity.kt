package com.example.kaizensport

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.kaizensport.presentation.ui.theme.KaizenSportTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KaizenSportTheme {
                // A surface container using the 'background' color from the theme

            }
        }
    }
}
