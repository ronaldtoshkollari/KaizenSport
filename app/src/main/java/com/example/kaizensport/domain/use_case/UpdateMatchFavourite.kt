package com.example.kaizensport.domain.use_case

import com.example.kaizensport.domain.model.MatchEvent
import com.example.kaizensport.domain.repository.KaizenSportRepository
import javax.inject.Inject

class UpdateMatchFavourite {


    operator fun invoke(matches: List<MatchEvent>, matchEvent: MatchEvent): List<MatchEvent> {

        val mutableMatches = matches.toMutableList()

        mutableMatches.remove(matchEvent)
        mutableMatches.add(matchEvent.copy(isEventFavourite = !matchEvent.isEventFavourite))
        return mutableMatches.toList()

    }


}