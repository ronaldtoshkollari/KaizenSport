package com.example.kaizensport.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SportInfoDto(
    @SerializedName("d")
    val category: String,
    @SerializedName("e")
    val events: List<MatchEventDto>,
    @SerializedName("i")
    val sportInfoId: String
)