package com.example.kaizensport.util.mappers

import com.example.kaizensport.data.local.entity.MatchEventEntity
import com.example.kaizensport.data.remote.dto.MatchEventDto
import com.example.kaizensport.domain.model.MatchEvent

object MatchEventMapper {

    fun dtoToEntity(matchEventDto: MatchEventDto): MatchEventEntity{
        return MatchEventEntity(
            eventId = matchEventDto.eventId,
            eventName = matchEventDto.eventName,
            eventStartTime = matchEventDto.eventStartTime,
            sportId = matchEventDto.sportId
        )
    }

    fun entityToModel(matchEventEntity: MatchEventEntity): MatchEvent {
        return MatchEvent(
            eventId = matchEventEntity.eventId,
            eventName = matchEventEntity.eventName,
            eventStartTime = matchEventEntity.eventStartTime,
            sportId = matchEventEntity.sportId
        )
    }

    fun modelToEntity(matchEvent: MatchEvent): MatchEventEntity{
        return MatchEventEntity(
            eventId = matchEvent.eventId,
            eventName = matchEvent.eventName,
            eventStartTime = matchEvent.eventStartTime,
            sportId = matchEvent.sportId,
            isEventFavourite = matchEvent.isEventFavourite
        )
    }

}