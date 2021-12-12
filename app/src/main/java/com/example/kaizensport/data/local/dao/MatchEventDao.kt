package com.example.kaizensport.data.local.dao

import androidx.room.*
import com.example.kaizensport.data.local.entity.MatchEventEntity

@Dao
interface MatchEventDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMatchEvent(matchEventEntity: MatchEventEntity)

    @Query("DELETE FROM matches WHERE eventId = :eventId")
    suspend fun deleteMatchEvent(eventId: String)

    @Update
    suspend fun updateMatchEvent(matchEventEntity: MatchEventEntity)

    @Query("DELETE FROM matches")
    suspend fun deleteAllMatchEvents()

    @Query("SELECT * FROM matches")
    suspend fun getAllMatchEvents(): List<MatchEventEntity>


}