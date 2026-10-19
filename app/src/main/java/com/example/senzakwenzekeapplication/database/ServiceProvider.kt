package com.example.senzakwenzekeapplication.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "providers")
data class ServiceProvider(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val skill: String,
    val phone: String,
    val location: String,
    val rating: Double,
    val pricePerHour: Double
)