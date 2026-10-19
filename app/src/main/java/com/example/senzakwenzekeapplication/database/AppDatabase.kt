package com.example.senzakwenzekeapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        User::class, 
        ServiceProvider::class, 
        Service::class, 
        Booking::class, 
        Review::class,
        ContactMessage::class,
        Favorite::class
    ],
    version = 2,  // Change to 2 because we added tables!
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "senzakwenzeke_database"
                )
                .fallbackToDestructiveMigration() // This deletes old data and creates new tables
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}