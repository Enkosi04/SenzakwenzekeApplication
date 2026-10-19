package com.example.senzakwenzekeapplication.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "services")
data class Service(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val providerId: Int,
    val name: String,
    val description: String,
    val price: String,
    val category: String
)