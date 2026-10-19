package com.example.senzakwenzekeapplication.database

import androidx.room.*

@Dao
interface AppDao {
    // User
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insertUser(user: User)
    @Query("SELECT * FROM users") suspend fun getAllUsers(): List<User>
    @Query("DELETE FROM users") suspend fun deleteAllUsers()

    // Provider
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insertProvider(p: ServiceProvider)
    @Query("SELECT * FROM providers") suspend fun getAllProviders(): List<ServiceProvider>
    @Query("DELETE FROM providers") suspend fun deleteAllProviders()

    // Service
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insertService(s: Service)
    @Query("SELECT * FROM services") suspend fun getAllServices(): List<Service>
    @Query("DELETE FROM services") suspend fun deleteAllServices()

    // Booking
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insertBooking(b: Booking)
    @Query("SELECT * FROM bookings") suspend fun getAllBookings(): List<Booking>
    @Query("DELETE FROM bookings") suspend fun deleteAllBookings()

    // Review
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insertReview(r: Review)
    @Query("SELECT * FROM reviews") suspend fun getAllReviews(): List<Review>
    @Query("DELETE FROM reviews") suspend fun deleteAllReviews()

    // Contact Messages
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insertContactMessage(msg: ContactMessage)
    @Query("SELECT * FROM contact_messages") suspend fun getAllMessages(): List<ContactMessage>
    @Query("DELETE FROM contact_messages") suspend fun deleteAllContactMessages()

    // Favorites
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insertFavorite(fav: Favorite)
    @Query("SELECT * FROM favorites") suspend fun getAllFavorites(): List<Favorite>
    @Query("DELETE FROM favorites") suspend fun deleteAllFavorites()
}