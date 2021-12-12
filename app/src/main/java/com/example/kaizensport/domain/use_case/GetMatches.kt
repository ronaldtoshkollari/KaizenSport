package com.example.kaizensport.domain.use_case

import com.example.kaizensport.domain.model.MatchEvent
import com.example.kaizensport.domain.repository.KaizenSportRepository
import com.example.kaizensport.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMatches @Inject constructor(
    private val repository: KaizenSportRepository
) {

    operator fun invoke(): Flow<Resource<List<MatchEvent>>> = repository.getMatchEvents()
}