package com.example.kaizensport.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kaizensport.R
import com.example.kaizensport.presentation.components.CategoryBar
import com.example.kaizensport.presentation.components.CustomTopAppBar
import com.example.kaizensport.presentation.components.MatchCard
import com.example.kaizensport.presentation.viewmodel.KaizenSportViewModel

@Composable
fun HomeScreen(
    viewModel: KaizenSportViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Scaffold(topBar = {
        CustomTopAppBar(title = "Kaizen Sport")
    }) {

        LazyColumn(contentPadding = PaddingValues(vertical = 24.dp)) {

            items(state.categoryList) { category ->

                var isExpanded = true
                when (category.id) {

                    "FOOT" -> {
                        isExpanded = state.isSoccerExpanded
                    }

                    "BASK" -> {
                        isExpanded = state.isBasketballExpanded
                    }

                    "TENN" -> {
                        isExpanded = state.isTennisExpanded
                    }

                    "TABL" -> {
                        isExpanded = state.isTableTennisExpanded
                    }

                    "VOLL" -> {
                        isExpanded = state.isVolleyballExpanded
                    }

                    "ESPS" -> {
                        isExpanded = state.isEsportsExpanded
                    }

                    "ICEH" -> {
                        isExpanded = state.isIceHockeyExpanded
                    }

                    "HAND" -> {
                        isExpanded = state.isHandballExpanded
                    }

                    "BCHV" -> {
                        isExpanded = state.isBeachVolleyExpanded
                    }

                    "SNOO" -> {
                        isExpanded = state.isSnookerExpanded
                    }

                    "BADM" -> {
                        isExpanded = state.isBadmintonExpanded
                    }

                }

                CategoryBar(category = category, isExpanded = isExpanded) {
                    viewModel.updateExpandCategory(category)
                }

                Spacer(modifier = Modifier.height(12.dp))

                if (isExpanded) {
                    LazyRow() {
                        items(viewModel.getMatchesOfCategory(category)) { matchEvent ->
                            MatchCard(
                                matchEvent = matchEvent,
                                updateFavouriteMatch = { viewModel.updateFavouriteMatch(matchEvent) },
                                updateCountDown = { viewModel.updateCountDown(it, matchEvent)} )
                            Spacer(modifier = Modifier.width(24.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

            }
        }


    }

}