package com.example.kaizensport.domain.repository

import com.example.kaizensport.domain.model.Category
import com.example.kaizensport.domain.model.MatchEvent
import com.example.kaizensport.util.Resource
import kotlinx.coroutines.flow.Flow

interface KaizenSportRepository {

    suspend fun getCategories(): Flow<Resource<List<Category>>>

    suspend fun getMatchEvents(): Flow<Resource<List<MatchEvent>>>

    suspend fun clearDatabase()

}