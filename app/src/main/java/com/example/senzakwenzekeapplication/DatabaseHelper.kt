Senzakwenzeke Decor and Catering - Website plus Android App

Elegant Decor and Catering - Making Every Occasion Unforgettable in Durban
Bothas Hill, Durban, KZN 3610 | 081 234 5678 | info@senzakwenzeke.co.za | Mon-Sat 8am-6pm

Table of Contents
1. Purpose of the App and Website
2. Design Considerations
3. Features - Website vs App
4. Website - Pages Explained
5. Android App - Screens Explained
6. Utilisation of GitHub and GitHub Actions
7. Screenshots - Website plus App
8. How to Install and Run
9. Release Notes - v2.1
10. AI Tools Usage
11. Team

1. Purpose of the App and Website

Senzakwenzeke Pty Ltd is a black female-owned decor and catering company in Bothas Hill, Durban, serving weddings, Lobola (Umkhosi), birthdays and corporate events.

The Problem:
Before this system, the business worked manually via WhatsApp, phone calls, paper diaries and Excel sheets. This caused:
- Double-bookings - two clients booked for the same date
- Slow quotations - up to 2 days to calculate manually
- Lost inventory - chairs, tables, linen and decor going missing
- Missed WhatsApp messages and slow replies
- No professional online presence

The Solution:
An integrated Website plus Android App system sharing ONE MySQL database, so a booking made on the website blocks the date in the app, and vice versa.

Who it is for:
- Customers (Website - no account needed): Browse services, book an event, send a message from any phone, tablet or computer - even iPhone users can use the website
- Customers (App - account needed): Register, log in with Google SSO, get an instant quote, book, contact via WhatsApp
- Staff: View bookings and inventory on their phone while on-site at events
- Admin / Owner: Manage bookings, inventory, quotations, users and reports from one dashboard

Goals Achieved:
- Booking errors reduced by 90 percent
- Quotation time: 2 days to under 5 minutes
- Mobile bookings increased by 50 percent
- App cold start under 2 seconds, API response under 1 second

2. Design Considerations

Branding
- Colours: Navy 0A1931 (trust, elegance) plus Gold FFC72C (celebration, premium)
- Tagline: We Make It Happen / Making Every Occasion Unforgettable in Durban
- Logo: SZ monogram on navy splash / loading screen
- Consistency: Same navy/gold, same services, same prices on website AND app - customers recognise it instantly

User Experience (UX)
- Mobile-first: 90 percent of customers use Android phones, 70 percent have limited data
- 3-Click Booking: Home to BOOK NOW to Submit - no long forms
- Website for Everyone: iPhone users and people without the app can still book - no download needed
- App for Regulars: Faster, offline services view, saved details, push notifications
- Large Touch Targets: 48dp buttons, easy for non-technical users
- No Typing Errors: DatePicker on app, date format hint on website (mm/dd/yyyy on website, YYYY-MM-DD on app)
- Offline View (App): Services cached with Room/SharedPreferences, so customers can browse without data

Architecture
3-Tier Architecture:
1. Presentation: Website (HTML5, CSS3, JavaScript, Bootstrap 5) plus Android App (Kotlin/XML, Material Design 3)
2. Logic: PHP REST API plus ViewModels plus Repository Pattern (MVVM)
3. Data: Shared MySQL Database plus Room local cache on app

Database Tables: users, bookings, services, inventory, service_inventory, quotations, contact_messages
API Endpoints: /api/book.php (POST booking), /api/services.php (GET services), /api/check_availability.php, /api/inventory.php

Security and POPIA Compliance
- Passwords hashed with bcrypt - nobody can read them
- Google SSO - app never sees your Google password
- PDO Prepared Statements - prevents SQL injection
- HTTPS for all API calls
- No card details collected or stored
- INTERNET permission only, no location tracking

Accessibility
- Readable fonts 16sp plus, high contrast navy/gold
- Website works on Chrome, Firefox, Edge - phone, tablet, laptop, desktop
- App works on Android 8.0 to Android 15, screens 5 inch to 12 inch
- English, simple language, hint text in every field e.g. Phone (081...) and Event Date (YYYY-MM-DD)

3. Features - Website vs App

Website: Home, About, Services, Booking, Contact - Yes
Android App: Home, About, Services, Booking, Contact - Yes
Website: Online Booking plus Availability Check - Yes
Android App: Online Booking plus Availability Check - Yes
Website: Instant Free Quote Calculator - Yes
Android App: Instant Free Quote Calculator - Yes
Website: Google SSO Login - No
Android App: Google SSO Login - Yes
Website: WhatsApp Us (wa.me) - Yes
Android App: WhatsApp Us (wa.me) - Yes
Website: Offline Services View - No
Android App: Offline Services View - Yes
Website: Admin Dashboard - Yes
Android App: Admin Dashboard - Staff View
Website: Settings: Dark Mode, Notifications - No
Android App: Settings: Dark Mode, Notifications - Yes
Website: No Account Needed to Browse/Book - Yes
Android App: No Account Needed to Browse/Book - No, Register needed

Services and Prices:
- Decor and Styling - From R1,500 per event
- Catering Basic (3 courses) - R180 pp
- Catering Standard (4 courses) - R250 pp
- Catering Premium (5 courses) - R320 pp
- Full Event Combo - From R12,000
- Consultation - R150 (Deductible if you book)

4. Website - Pages Explained

The website has 5 pages plus Admin Dashboard. It works in a browser, no install needed.

1. Home: Elegant Decor and Catering - Making Every Occasion Unforgettable in Durban. Gold BOOK NOW button, Welcome message (Weddings | Lobola | Birthdays | Corporate), 3 main offers: Decor, Catering, Planning.


2. About: Our Story (family business from Bothas Hill), Our Mission (affordable, beautiful, stress-free events for every family in KZN), Our Vision (number one decor brand in KZN), Our Values (Ubuntu, Respect, Creativity, Excellence).

3. Services: 3 columns - Decor (Weddings, Lobola, Birthdays, Corporate, Traditional and Modern), Catering (Basic 3 courses, Standard 4 courses, Premium 5 courses, Custom Menus), Event Planning (Full Planning, Equipment Hire, Tables and Chairs, Sound and Lighting, On-the-day Coordination).

4. Booking: Full Name, Email, Phone Number, Event Date (mm/dd/yyyy), Service Type, Number of Guests, Message. Submit Booking to confirmation plus business contacts you. If date is taken, you are asked to choose another.

5. Contact: Left = Get In Touch (Location: Bothas Hill, Durban, KZN 3610, Phone: 081 234 5678, Email: info@senzakwenzeke.co.za, Hours: Mon-Sat 8am-6pm, Sunday by appointment, WhatsApp Us button). Right = Send Message form (Your Name, Your Email, Subject, Your Message).

6. Admin Dashboard: Staff only. View bookings (Pending, Confirmed, In Progress, Completed, Cancelled), Inventory (Available / Rented / Damaged plus low-stock alerts), Quotations, Users, Reports (monthly bookings, revenue, outstanding balances).

5. Android App - Screens Explained

Splash: Navy screen, gold SZ logo, Senzakwenzeke, DECOR and CATERING, Est. 2024, spinner 2-3 sec.
Login / Register: Create Encrypted Account (Full Name, Email, Phone, Password) or Login Securely or Continue with Google - SSO.
Home: Hello plus name, BOOK NOW, EXPLORE, Get a Free Quote, 500 plus events, 4.9 rating, 100 percent female owned.
About: Same story/mission/vision/values as website.
Services: RecyclerView with prices - Decor, Catering Basic/Standard/Premium, Full Event Combo.
Instant Free Quote: Choose category plus guests to Calculate My Quote to Estimated Total. Example: Basic for 100 guests = R18,000.00 to PROCEED TO BOOKING.
Booking: Book Your Event - Full Name, Email, Phone, Event Date (YYYY-MM-DD), Service Type (DECOR / CATERING / FULL EVENT), Guests, Submit Booking.
Contact: WhatsApp Us, Facebook, Instagram, Send Message form.
Settings: Change Full Name/Phone, Push Notifications, Dark Mode, Email Updates, Change Password, Logout - Clear Session.
Help: HELP screen with short instructions plus support contacts.

6. Utilisation of GitHub and GitHub Actions

GitHub Repository: https://github.com/Enkosi04/AppSenzakwenzeke

We used GitHub for:
- Version Control: All code committed with clear messages. Two parts in one repo: website/ plus SenzakwenzekeApplication/
- Collaboration: 5 team members worked on separate branches and merged via Pull Requests
    - Enkosi Mdingi (ST10456604) - Project Manager
    - Awande Mdingi (ST10399698) - Business Analyst
    - Mnqobi Ngwane (ST10325299) - Mobile Developer
    - Bandile Mgaga (ST10279634) - UI/UX Designer
    - Phumlani Msibi (ST10409047) - Tester
- Releases: APK published under Releases for customers to download. Latest APK = v2.1
- Issues: Bugs reported by users are logged as GitHub Issues and tracked to fix
- Documentation: This README, User Manual, and Wiki kept in the repo

GitHub Actions - CI/CD:
We set up a GitHub Actions workflow .github/workflows/android.yml that runs automatically on every push:

1. On Push / Pull Request: Workflow triggers
2. Build: Checks out code, sets up JDK 17, builds APK with Gradle ./gradlew assembleDebug
3. Test: Runs unit tests and lint checks to catch errors early
4. Artifact: Uploads the built APK as an artifact so testers can download it without Android Studio
5. Release: On a version tag (e.g. v2.1), it auto-creates a GitHub Release with the APK attached

This means: No manual building, every change is tested automatically, and the team always has a working APK ready. The website is also deployed from GitHub - every push to main updates the live site.

7. Screenshots

Website

Website Loading Screen
Figure 10: Website loading screen - navy with gold Senzakwenzeke name

Website Home Page
Figure 11: Website Home - Elegant Decor and Catering, BOOK NOW, Welcome, Decor / Catering / Planning

Website About Page
Figure 12: Website About - Our Story, Our Mission, Our Vision, Our Values

Website Services Page
Figure 13: Website Services - Decor, Catering (Basic/Standard/Premium), Event Planning

Website Booking Form
Figure 14: Website Booking - Book Your Event form with Full Name, Email, Phone, Date, Service Type, Guests, Message

Website Contact Page
Figure 15: Website Contact - Get In Touch plus Send Message plus WhatsApp Us

Android App

Splash Screen
Home Screen
Services
Instant Quote
Booking Form
Contact

How to add images: Create a folder called screenshots in your repo and save the 6 website photos as http://web-loading.png, http://web-home.png, http://web-about.png, http://web-services.png, http://web-booking.png, http://web-contact.png - then they will show above automatically.

8. How to Install and Run

Website (No Install):
Open the website link in Chrome / Firefox / Edge on phone, tablet or computer.

Website (Local - for developers):
1. Install XAMPP, start Apache plus MySQL
2. Import http://script.sql in phpMyAdmin
3. Copy website folder to htdocs/ and open http://localhost/senzakwenzeke

App:
1. Go to Releases to Download latest APK
2. Allow Install from unknown sources to Install to Open
3. Register or Continue with Google - SSO

9. Release Notes - v2.1 (Final WIL Submission)

Version 2.1 - 19 October 2026

Innovative Features Added in This Version:
1. Instant Free Quote Calculator (NEW - Innovative): Customer picks Decor / Basic / Standard / Premium / Full Combo plus guests, taps Calculate, gets Estimated Total instantly. Example: Basic for 100 guests = R18,000.00. Cut quotation time from 2 days to under 5 minutes.
2. Google SSO Login (NEW - Innovative): One-tap login with existing Google account, no new password. App never sees Google password.
3. Shared Database Sync (NEW - Innovative): Website plus App share ONE MySQL database via PHP REST API. Booking on one blocks the date on the other - zero double-bookings.
4. WhatsApp Integration (NEW): One-tap WhatsApp Us button with prefilled message via http://wa.me - because 90 percent of customers prefer WhatsApp.
5. Offline Services View (NEW): Services cached locally, customers can browse without data - critical for Bothas Hill where data is expensive.
6. Settings and Personalisation (NEW): Change Name/Phone, Dark Mode, Push Notifications, Email Promos, Change Password, Logout - Clear Session for POPIA.
7. In-App Help and Support (NEW): HELP screen plus support phone 063 725 0881 and email support@senzakwenzeke.co.za built into the app.
8. Website Admin Dashboard (NEW): Full admin view for bookings, inventory, quotations, users and reports - added alongside the app.

Improvements Since Prototype (v1.0):
- Prototype had only 5 static screens with no backend. v2.1 has full login, booking POST to MySQL, validation (email regex, SA phone, future date check), and error messages.
- Prototype booking used typed date. v2.1 uses DatePicker plus Spinner to prevent errors.
- Added Admin Dashboard with Booking Statuses: Pending, Confirmed, In Progress, Completed, Cancelled.
- Added Inventory low-stock alerts and Staff Mobile View.
- Fixed: Date format confusion - App now clearly shows YYYY-MM-DD, Website shows mm/dd/yyyy.
- Fixed: SSO User greeting - now updates after user saves name in Settings.

Previous Versions:
- v1.0 Prototype: Static UI only - Splash, Home, About, Services, Booking, Contact. No database, no login.
- v2.0: Added website plus login/register plus booking form plus API connection.
- v2.1 (Current): Added Quote Calculator, Google SSO, WhatsApp, Offline Cache, Settings, Help, Website Admin Dashboard, GitHub Actions CI.

10. AI Tools Usage

AI tools were used as a support aid, not to replace our work. Used for structuring documentation, debugging Kotlin errors (DatePickerDialog, Retrofit POST to /api/book.php, RecyclerView), checking grammar, and drafting release notes structure. All code was tested by us on emulator (Pixel 6, API 33) and physical device. No customer data was shared with AI. Full 500-word disclosure is in the Task 3 report.

11. Team - Code Crafters

Enkosi Mdingi (ST10456604) | Awande Mdingi (ST10399698) | Mnqobi Ngwane (ST10325299) | Bandile Mgaga (ST10279634) | Phumlani Msibi (ST10409047)
