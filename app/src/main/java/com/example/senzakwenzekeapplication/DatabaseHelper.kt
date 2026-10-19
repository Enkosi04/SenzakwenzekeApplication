package com.example.senzakwenzekeapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.mindrot.jbcrypt.BCrypt

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "senzakwenzeke.db", null, 2) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE Users(user_id INTEGER PRIMARY KEY AUTOINCREMENT, full_name TEXT, email TEXT UNIQUE, phone TEXT, password_encrypted TEXT)")
        db.execSQL("CREATE TABLE Services(service_id INTEGER PRIMARY KEY, name TEXT, price TEXT, description TEXT)")
        db.execSQL("CREATE TABLE Bookings(booking_id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, service_id INTEGER, event_date TEXT, guests TEXT, location TEXT, status TEXT)")
        db.execSQL("CREATE TABLE Feedback(message_id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, message TEXT, rating INTEGER)")

        seedData(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Users")
        db.execSQL("DROP TABLE IF EXISTS Services")
        db.execSQL("DROP TABLE IF EXISTS Bookings")
        db.execSQL("DROP TABLE IF EXISTS Feedback")
        onCreate(db)
    }

    private fun seedData(db: SQLiteDatabase) {
        // Seed 10 Users
        val pass = BCrypt.hashpw("123456", BCrypt.gensalt())
        for (i in 1..10) {
            val cv = ContentValues().apply {
                put("full_name", "Test User $i")
                put("email", "user$i@example.com")
                put("phone", "012345678$i")
                put("password_encrypted", pass)
            }
            db.insert("Users", null, cv)
        }

        // Seed 10 Services
        val services = listOf(
            Pair("Decor", "R1500"), Pair("Basic Catering", "R180pp"), Pair("Standard Catering", "R250pp"),
            Pair("Premium Catering", "R320pp"), Pair("Lobola", "R5500"), Pair("White Wedding", "R8500"),
            Pair("Full Combo", "R12000"), Pair("Birthdays", "R2000"), Pair("Corporate", "R4000"),
            Pair("Consultation", "R150")
        )
        services.forEach {
            val cv = ContentValues().apply {
                put("name", it.first)
                put("price", it.second)
                put("description", "Premium quality ${it.first} service in Bothas Hill.")
            }
            db.insert("Services", null, cv)
        }

        // Seed 10 Bookings
        val locations = listOf("Bothas Hill", "Hillcrest", "Kloof", "Pinetown", "Durban", "Westville", "Umlazi", "Berea", "Morningside", "Mount Edgecombe")
        for (i in 1..10) {
            val cv = ContentValues().apply {
                put("user_id", i)
                put("service_id", i)
                put("event_date", "2024-11-$i")
                put("guests", "${i * 10}")
                put("location", locations[i-1])
                put("status", "Pending")
            }
            db.insert("Bookings", null, cv)
        }

        // Seed 10 Feedback
        for (i in 1..10) {
            val cv = ContentValues().apply {
                put("user_id", i)
                put("message", "Great service from Senzakwenzeke!")
                put("rating", 5)
            }
            db.insert("Feedback", null, cv)
        }
    }
}
