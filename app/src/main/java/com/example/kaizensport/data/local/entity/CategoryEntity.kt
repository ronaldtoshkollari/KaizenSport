package com.example.kaizensport.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sport_categories")
data class CategoryEntity(
    @PrimaryKey
    val id: String,
    val name: String
)
