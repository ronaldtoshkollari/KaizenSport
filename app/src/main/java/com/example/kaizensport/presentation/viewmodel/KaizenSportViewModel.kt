package com.example.kaizensport.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kaizensport.domain.model.Category
import com.example.kaizensport.domain.model.MatchEvent
import com.example.kaizensport.domain.use_case.GetCategories
import com.example.kaizensport.domain.use_case.GetMatches
import com.example.kaizensport.domain.use_case.UpdateEventCountDown
import com.example.kaizensport.domain.use_case.UpdateMatchFavourite
import com.example.kaizensport.util.DateConverter
import com.example.kaizensport.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KaizenSportViewModel @Inject constructor(
    private val getCategories: GetCategories,
    private val getMatches: GetMatches,
    private val updateMatchFavourite: UpdateMatchFavourite,
    private val updateEventCountDown: UpdateEventCountDown
) : ViewModel() {

    private val _state = mutableStateOf(KaizenSportState())
    val state: State<KaizenSportState> = _state

    init {
        initialCategories()
        initialMatches()
    }

    private fun initialCategories() {


        getCategories().onEach { result ->

            when (result) {

                is Resource.Loading -> {
                    _state.value = KaizenSportState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = KaizenSportState(
                        message = result.message ?: "An unexpected error occurred."
                    )
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(categoryList = result.data ?: emptyList())
                }

            }

        }.launchIn(viewModelScope)

    }

    private fun initialMatches() {

        getMatches().onEach { result ->
            when (result) {

                is Resource.Loading -> {
                    _state.value = KaizenSportState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = KaizenSportState(
                        message = result.message ?: "An unexpected error occurred."
                    )
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(matchesList = result.data ?: emptyList())
                }

            }
        }.launchIn(viewModelScope)

    }

    fun getMatchesOfCategory(category: Category): List<MatchEvent> {

        return _state.value.matchesList.filter { it.sportId == category.id }
            .sortedByDescending { it.isEventFavourite }


    }

    fun updateFavouriteMatch(matchEvent: MatchEvent) {

        val updatedMatches = updateMatchFavourite(_state.value.matchesList, matchEvent)

        _state.value = state.value.copy(matchesList = updatedMatches.sortedByDescending { it.isEventFavourite }, message = "${matchEvent.eventName} added to Favourite")

    }

    fun updateExpandCategory(category: Category) {

        when(category.id) {


            "FOOT" -> {
                _state.value = _state.value.copy(isSoccerExpanded = !_state.value.isSoccerExpanded)
            }

            "BASK" -> {
                _state.value = _state.value.copy(isBasketballExpanded = !_state.value.isBasketballExpanded)
            }

            "TENN" -> {
                _state.value = _state.value.copy(isTennisExpanded = !_state.value.isTennisExpanded)
            }

            "TABL" -> {
                _state.value = _state.value.copy(isTableTennisExpanded = !_state.value.isTableTennisExpanded)
            }

            "VOLL" -> {
                _state.value = _state.value.copy(isVolleyballExpanded = !_state.value.isVolleyballExpanded)
            }

            "ESPS" -> {
                _state.value = _state.value.copy(isEsportsExpanded = !_state.value.isEsportsExpanded)
            }

            "ICEH" -> {
                _state.value = _state.value.copy(isIceHockeyExpanded = !_state.value.isIceHockeyExpanded)
            }

            "HAND" -> {
                _state.value = _state.value.copy(isHandballExpanded = !_state.value.isHandballExpanded)
            }

            "BCHV" -> {
                _state.value = _state.value.copy(isBeachVolleyExpanded = !_state.value.isBeachVolleyExpanded)
            }

            "SNOO" -> {
                _state.value = _state.value.copy(isSnookerExpanded = !_state.value.isSnookerExpanded)
            }

            "BADM" -> {
                _state.value = _state.value.copy(isBadmintonExpanded = !_state.value.isBadmintonExpanded)
            }

        }

    }

    fun updateCountDown(newTime: String, matchEvent: MatchEvent){

        val matches = _state.value.matchesList
        matches.forEach {
            if (it.eventId == matchEvent.eventId){
                it.eventStartTime = newTime
            }
        }



    }


}