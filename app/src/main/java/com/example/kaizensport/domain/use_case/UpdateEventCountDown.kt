package com.example.kaizensport.domain.use_case

import com.example.kaizensport.domain.model.MatchEvent


class UpdateEventCountDown {


    operator fun invoke(matches: List<MatchEvent>, matchEvent: MatchEvent): List<MatchEvent> {
        val mutableMatches = matches.toMutableList()

        mutableMatches.remove(matchEvent)
        mutableMatches.add(matchEvent.copy(eventStartTime = matchEvent.eventStartTime))
        return mutableMatches.toList()
    }

}