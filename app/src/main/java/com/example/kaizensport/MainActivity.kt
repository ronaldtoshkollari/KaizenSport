package com.example.kaizensport

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kaizensport.domain.model.MatchEvent
import com.example.kaizensport.presentation.components.MatchCard
import com.example.kaizensport.presentation.screen.HomeScreen
import com.example.kaizensport.presentation.ui.theme.KaizenSportTheme
import com.example.kaizensport.presentation.viewmodel.KaizenSportViewModel
import com.example.kaizensport.util.DateConverter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            KaizenSportTheme {

                Column {
                    HomeScreen()
                }

                // A surface container using the 'background' color from the theme

            }
        }
    }
}
