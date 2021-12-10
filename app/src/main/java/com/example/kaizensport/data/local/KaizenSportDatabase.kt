package com.example.kaizensport.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kaizensport.data.local.dao.CategoryDao
import com.example.kaizensport.data.local.dao.MatchEventDao
import com.example.kaizensport.data.local.entity.CategoryEntity
import com.example.kaizensport.data.local.entity.MatchEventEntity

@Database(
    entities = [(CategoryEntity::class), (MatchEventEntity::class)],
    version = 1,
    exportSchema = false
)
abstract class KaizenSportDatabase: RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun matchEventDao(): MatchEventDao
}