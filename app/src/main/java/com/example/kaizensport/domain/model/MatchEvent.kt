package com.example.kaizensport.domain.model

data class MatchEvent(
    val eventId: String,
    val eventName: String,
    val eventStartTime: String,
    val isEventFavourite: Boolean = false,
    val sportId: String
)