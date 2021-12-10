package com.example.kaizensport.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MatchEventDto(

    @SerializedName("d")
    val eventName: String,
    @SerializedName("i")
    val eventId: String,
    val sh: String,
    @SerializedName("si")
    val sportId: String,
    @SerializedName("tt")
    val eventStartTime: String
)