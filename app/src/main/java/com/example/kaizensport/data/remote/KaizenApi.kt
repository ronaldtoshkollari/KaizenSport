package com.example.kaizensport.data.remote

import com.example.kaizensport.data.remote.dto.SportInfoDto
import retrofit2.http.GET

interface KaizenApi {

    @GET("api/sports")
    suspend fun getMatchesInfo(): List<SportInfoDto>

}