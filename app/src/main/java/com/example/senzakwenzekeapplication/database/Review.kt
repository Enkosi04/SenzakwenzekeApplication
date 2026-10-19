package com.example.senzakwenzekeapplication.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reviews")
data class Review(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val providerId: Int,
    val userId: Int,
    val rating: Double,
    val comment: String,
    val date: String
)