package com.example.senzakwenzekeapplication.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object DatabaseSeeder {
    suspend fun seedDatabase(dao: AppDao) {
        withContext(Dispatchers.IO) {
            // Clear existing data to ensure a fresh start with Senzakwenzeke-specific data
            dao.deleteAllUsers()
            dao.deleteAllProviders()
            dao.deleteAllServices()
            dao.deleteAllBookings()
            dao.deleteAllReviews()
            dao.deleteAllContactMessages()
            dao.deleteAllFavorites()

            // 1. Users (Clients)
            val users = listOf(
                User(1, "Thando Nkosi", "thando@gmail.com", "0821234567", "Umlazi", "123456"),
                User(2, "Sarah Smith", "sarah@gmail.com", "0821234568", "Durban North", "123456"),
                User(3, "Mandla Zulu", "mandla@gmail.com", "0821234569", "Chatsworth", "123456"),
                User(4, "Noluthando Dlamini", "nolu@gmail.com", "0821234570", "Pinetown", "123456"),
                User(5, "John Peters", "john@gmail.com", "0821234571", "Berea", "123456"),
                User(6, "Precious Mkhize", "precious@gmail.com", "0821234572", "Isipingo", "123456"),
                User(7, "Sipho Ngcobo", "sipho@gmail.com", "0821234573", "Kloof", "123456"),
                User(8, "Ayesha Patel", "ayesha@gmail.com", "0821234574", "Overport", "123456"),
                User(9, "Linda Khumalo", "linda@gmail.com", "0821234575", "Morningside", "123456"),
                User(10, "Chris Brown", "chris@gmail.com", "0821234576", "Westville", "123456")
            )
            users.forEach { dao.insertUser(it) }

            // 2. Providers (Internal Senzakwenzeke Teams & Specialists)
            val providers = listOf(
                ServiceProvider(1, "Senza Decor Team Alpha", "Decor & Styling", "0812345671", "Bothas Hill", 4.9, 500.0),
                ServiceProvider(2, "Kwenzeke Catering Elite", "Catering", "0812345672", "Bothas Hill", 4.8, 450.0),
                ServiceProvider(3, "Traditional Arts Crew", "Cultural Decor", "0812345673", "Hillcrest", 5.0, 600.0),
                ServiceProvider(4, "Floral Magic Team", "Floristry", "0812345674", "Bothas Hill", 4.7, 400.0),
                ServiceProvider(5, "Modern Setup Group", "Stage Technicals", "0812345675", "Pinetown", 4.6, 350.0),
                ServiceProvider(6, "Zulu Heritage Stylists", "Cultural Decor", "0812345676", "Umlazi", 4.9, 550.0),
                ServiceProvider(7, "Boutique Buffet Chefs", "Catering", "0812345677", "Westville", 4.8, 480.0),
                ServiceProvider(8, "Elegant Drapes Team", "Decor", "0812345678", "Durban Central", 4.9, 520.0),
                ServiceProvider(9, "Event Ambiance Crew", "Lighting", "0812345679", "Kloof", 4.5, 400.0),
                ServiceProvider(10, "Senzakwenzeke Planners", "Coordination", "0812345680", "Bothas Hill", 4.8, 500.0)
            )
            providers.forEach { dao.insertProvider(it) }

            // 3. Services (Senzakwenzeke Packages)
            val servicesList = listOf(
                Service(1, 1, "Lobola Stage Setup", "Full traditional stage with premium backdrop", "R5500", "Decor"),
                Service(2, 2, "Standard Buffet Service", "Full 3-course menu for 50+ guests", "R250 pp", "Catering"),
                Service(3, 10, "Full Wedding Planning", "End-to-end wedding coordination & management", "R12000", "Planning"),
                Service(4, 4, "Floral Arch & Aisle", "Premium fresh flower arrangements for ceremonies", "R3500", "Decor"),
                Service(5, 2, "Basic Party Platter", "Assorted finger foods and light refreshments", "R180 pp", "Catering"),
                Service(6, 6, "Traditional Zulu Decor", "Authentic cultural styling, skins and props", "R4500", "Decor"),
                Service(7, 2, "Premium Gala Dinner", "High-end 5-course fine dining experience", "R320 pp", "Catering"),
                Service(8, 8, "Corporate Event Decor", "Professional branding and office party styling", "R4000", "Decor"),
                Service(9, 1, "Themed Birthday Setup", "Customized colors, balloons and accessories", "R2500", "Decor"),
                Service(10, 10, "Event Consultation", "Professional venue assessment and event advice", "R150", "Planning")
            )
            servicesList.forEach { dao.insertService(it) }

            // 4. Bookings (Historical and Upcoming Events)
            val bookings = listOf(
                Booking(1, 1, 1, 1, "Lobola Stage Setup", "2024-11-15", "09:00", "Thando Nkosi", "Confirmed"),
                Booking(2, 2, 2, 2, "Standard Buffet Service", "2024-12-05", "12:00", "Sarah Smith", "Pending"),
                Booking(3, 3, 6, 6, "Traditional Zulu Decor", "2024-11-20", "10:00", "Mandla Zulu", "Confirmed"),
                Booking(4, 4, 3, 10, "Full Wedding Planning", "2025-03-22", "08:00", "Noluthando Dlamini", "Confirmed"),
                Booking(5, 5, 5, 2, "Basic Party Platter", "2024-11-30", "14:00", "John Peters", "Pending"),
                Booking(6, 6, 4, 4, "Floral Arch & Aisle", "2024-12-10", "15:00", "Precious Mkhize", "Confirmed"),
                Booking(7, 7, 10, 10, "Event Consultation", "2024-11-08", "09:30", "Sipho Ngcobo", "Completed"),
                Booking(8, 8, 7, 2, "Premium Gala Dinner", "2024-12-18", "18:00", "Ayesha Patel", "Pending"),
                Booking(9, 9, 9, 1, "Themed Birthday Setup", "2024-11-25", "10:30", "Linda Khumalo", "Confirmed"),
                Booking(10, 10, 8, 8, "Corporate Event Decor", "2024-11-28", "13:00", "Chris Brown", "Completed")
            )
            bookings.forEach { dao.insertBooking(it) }

            // 5. Reviews (Customer Feedback)
            val reviews = listOf(
                Review(1, 1, 1, 5.0, "The Lobola setup was absolutely breathtaking!", "2024-10-20"),
                Review(2, 2, 2, 5.0, "Food was excellent, our guests couldn't stop eating.", "2024-10-21"),
                Review(3, 6, 3, 4.0, "Very authentic traditional styling, really impressed.", "2024-10-22"),
                Review(4, 10, 4, 5.0, "Senzakwenzeke made our wedding day perfect.", "2024-10-23"),
                Review(5, 2, 5, 4.0, "Great catering for our small party.", "2024-10-24"),
                Review(6, 4, 6, 5.0, "The floral arrangements were fresh and stunning.", "2024-10-25"),
                Review(7, 10, 7, 3.0, "Good consultation, looking forward to the event.", "2024-10-26"),
                Review(8, 2, 8, 5.0, "Best buffet service we've ever had in Durban.", "2024-10-27"),
                Review(9, 1, 9, 4.0, "Lovely themed setup for my daughter's birthday.", "2024-10-28"),
                Review(10, 8, 10, 5.0, "Professional decor for our year-end function.", "2024-10-29")
            )
            reviews.forEach { dao.insertReview(it) }

            // 6. Contact Messages (Inquiries)
            val messages = listOf(
                ContactMessage(1, "Nomusa", "nomusa@test.com", "Interested in a quote for a Lobola package.", "2024-11-01"),
                ContactMessage(2, "Bheki", "bheki@test.com", "Do you provide decor for outdoor venues?", "2024-11-02"),
                ContactMessage(3, "Zama", "zama@test.com", "Need catering for a 100-person wedding.", "2024-11-03"),
                ContactMessage(4, "Nandi", "nandi@test.com", "Love your portfolio! Are you free on Dec 16th?", "2024-11-04"),
                ContactMessage(5, "Paul", "paul@test.com", "Is the consultation fee deductable from the booking?", "2024-11-05"),
                ContactMessage(6, "Grace", "grace@test.com", "Checking availability for my birthday party.", "2024-11-06"),
                ContactMessage(7, "David", "david@test.com", "Can you customize a menu for a halaal event?", "2024-11-07"),
                ContactMessage(8, "Fatima", "fatima@test.com", "Do you offer rentals for traditional Zulu props?", "2024-11-08"),
                ContactMessage(9, "Sbu", "sbu@test.com", "Excellent service yesterday, just wanted to say thanks!", "2024-11-09"),
                ContactMessage(10, "Lisa", "lisa@test.com", "How far in advance should I book for a wedding?", "2024-11-10")
            )
            messages.forEach { dao.insertContactMessage(it) }

            // 7. Favorites (Customer Saved Items)
            val favorites = listOf(
                Favorite(1, 1, 1),
                Favorite(2, 1, 6),
                Favorite(3, 2, 2),
                Favorite(4, 2, 10),
                Favorite(5, 3, 4),
                Favorite(6, 3, 8),
                Favorite(7, 4, 3),
                Favorite(8, 4, 6),
                Favorite(9, 5, 1),
                Favorite(10, 5, 2)
            )
            favorites.forEach { dao.insertFavorite(it) }
        }
    }
}
