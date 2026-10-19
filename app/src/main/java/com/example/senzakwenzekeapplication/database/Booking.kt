package com.example.senzakwenzekeapplication.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookings")
data class Booking(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val serviceId: Int,
    val providerId: Int,
    val serviceName: String,
    val date: String,
    val time: String,
    val clientName: String,
    val status: String = "Pending"
)