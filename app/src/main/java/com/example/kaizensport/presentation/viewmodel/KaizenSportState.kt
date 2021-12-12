package com.example.kaizensport.presentation.viewmodel

import com.example.kaizensport.domain.model.Category
import com.example.kaizensport.domain.model.MatchEvent

data class KaizenSportState(
    val categoryList: List<Category> = emptyList(),
    val matchesList: List<MatchEvent> = emptyList(),
    val isLoading: Boolean = false,
    val message: String = "",
    val isSoccerExpanded: Boolean = true,
    val isBasketballExpanded: Boolean = true,
    val isTennisExpanded: Boolean = true,
    val isTableTennisExpanded: Boolean = true,
    val isVolleyballExpanded: Boolean = true,
    val isEsportsExpanded: Boolean = true,
    val isIceHockeyExpanded: Boolean = true,
    val isHandballExpanded: Boolean = true,
    val isBeachVolleyExpanded: Boolean = true,
    val isSnookerExpanded: Boolean = true,
    val isBadmintonExpanded: Boolean = true,

)
