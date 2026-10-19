package com.example.senzakwenzekeapplication

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.example.senzakwenzekeapplication.database.AppDatabase
import com.example.senzakwenzekeapplication.database.DatabaseSeeder
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var container: FrameLayout
    private var selectedService = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences("SenzakwenzekePrefs", Context.MODE_PRIVATE)
        val isDarkMode = prefs.getBoolean("darkMode", false)
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )

        setContentView(R.layout.activity_main)

        updateHeaderName()

        val roomDb = AppDatabase.getDatabase(this)
        lifecycleScope.launch {
            DatabaseSeeder.seedDatabase(roomDb.appDao())
        }

        container = findViewById(R.id.page_container)

        // Restore last page state (important for theme changes)
        val lastPage = prefs.getInt("lastPage", R.layout.activity_home_page)
        show(lastPage)

        findViewById<Button>(R.id.btnHome).setOnClickListener { show(R.layout.activity_home_page) }
        findViewById<Button>(R.id.btnExplore).setOnClickListener { show(R.layout.activity_explore) }
        findViewById<Button>(R.id.btnAbout).setOnClickListener { show(R.layout.activity_about) }
        findViewById<Button>(R.id.btnServices).setOnClickListener { show(R.layout.activity_services) }
        findViewById<Button>(R.id.btnBooking).setOnClickListener { show(R.layout.activity_booking) }
        findViewById<Button>(R.id.btnContact).setOnClickListener { show(R.layout.activity_contact) }
        findViewById<Button>(R.id.btnSettings).setOnClickListener { show(R.layout.activity_settings) }

        findViewById<Button>(R.id.btnHelp).setOnClickListener {
            startActivity(Intent(this, HelpActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        updateHeaderName()
    }

    private fun updateHeaderName() {
        val prefs = getSharedPreferences("SenzakwenzekePrefs", MODE_PRIVATE)
        val userName = prefs.getString("userName", "User")
        findViewById<TextView>(R.id.tvHeaderName)?.text = "HELLO, ${userName?.uppercase()}"
    }

    private fun show(layoutId: Int) {
        getSharedPreferences("SenzakwenzekePrefs", MODE_PRIVATE).edit().putInt("lastPage", layoutId).apply()

        container.removeAllViews()
        val view = layoutInflater.inflate(layoutId, container, false)
        container.addView(view)
        setupViewLogic(view, layoutId)
    }

    private fun setupViewLogic(view: View, layoutId: Int) {
        val prefs = getSharedPreferences("SenzakwenzekePrefs", MODE_PRIVATE)

        when (layoutId) {
            R.layout.activity_home_page -> {
                val userName = prefs.getString("userName", "User")
                view.findViewById<TextView>(R.id.tvWelcomeUser)?.text = "Welcome back, $userName"

                view.findViewById<MaterialButton>(R.id.btnExplore)?.setOnClickListener { show(R.layout.activity_explore) }
                view.findViewById<MaterialButton>(R.id.btnBookNow)?.setOnClickListener { show(R.layout.activity_booking) }
                view.findViewById<MaterialButton>(R.id.btnGoBooking)?.setOnClickListener { show(R.layout.activity_quotation) }

                view.findViewById<View>(R.id.btnFacebookH)?.setOnClickListener { openUrl("https://www.facebook.com") }
                view.findViewById<View>(R.id.btnInstagramH)?.setOnClickListener { openUrl("https://www.instagram.com") }
                view.findViewById<View>(R.id.btnWhatsAppH)?.setOnClickListener { openUrl("https://wa.me/27812345678") }
            }
            R.layout.activity_explore -> {
                // Featured Package Booking
                view.findViewById<MaterialButton>(R.id.btnBookFeatured)?.setOnClickListener {
                    selectedService = "Decor" // Or specific package ID
                    show(R.layout.activity_booking)
                }
                
                // Calendar Check
                view.findViewById<MaterialButton>(R.id.btnViewCalendar)?.setOnClickListener {
                    showInfoDialog("Availability Calendar", "Next available date for full setup: 15 November 2024.\n\nNote: December is currently 90% booked. Please secure your date soon.")
                }
                
                // Live Stats Simulation (In a real app, this would come from a Repository/Firebase)
                view.findViewById<TextView>(R.id.tvLiveEvents)?.text = "124+"
            }
            R.layout.activity_quotation -> {
                val rgServiceType = view.findViewById<RadioGroup>(R.id.rgServiceType)
                val etGuestsCount = view.findViewById<TextInputEditText>(R.id.etGuestsCount)
                val btnCalculate = view.findViewById<MaterialButton>(R.id.btnCalculateQuote)
                val layoutResult = view.findViewById<LinearLayout>(R.id.layoutResult)
                val tvTotal = view.findViewById<TextView>(R.id.tvTotalQuote)
                val btnBookFromQuote = view.findViewById<MaterialButton>(R.id.btnBookFromQuote)

                btnCalculate?.setOnClickListener {
                    val selectedId = rgServiceType?.checkedRadioButtonId
                    val guestsStr = etGuestsCount?.text.toString().trim()
                    
                    if (selectedId == -1 || selectedId == null) {
                        Toast.makeText(this, "Please select a service type", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }

                    var total = 0.0
                    val guests = if (guestsStr.isEmpty()) 0 else guestsStr.toInt()

                    when (selectedId) {
                        R.id.rbDecor -> total = 1500.0
                        R.id.rbCateringBasic -> total = 180.0 * guests
                        R.id.rbCateringStandard -> total = 250.0 * guests
                        R.id.rbCateringPremium -> total = 320.0 * guests
                        R.id.rbFullCombo -> total = 12000.0
                    }

                    if ((selectedId == R.id.rbCateringBasic || selectedId == R.id.rbCateringStandard || selectedId == R.id.rbCateringPremium) && guests == 0) {
                        Toast.makeText(this, "Please enter number of guests for catering", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }

                    tvTotal?.text = "R${String.format("%.2f", total)}"
                    layoutResult?.visibility = View.VISIBLE
                }

                btnBookFromQuote?.setOnClickListener {
                    show(R.layout.activity_booking)
                }
            }
            R.layout.activity_about -> {
                view.findViewById<MaterialButton>(R.id.btnViewServices)?.setOnClickListener { show(R.layout.activity_services) }
            }
            R.layout.activity_services -> {
                view.findViewById<MaterialButton>(R.id.btnBookNow)?.setOnClickListener { show(R.layout.activity_booking) }
            }
            R.layout.activity_contact -> {
                view.findViewById<MaterialButton>(R.id.btnWhatsApp)?.setOnClickListener { openUrl("https://wa.me/27812345678") }
                view.findViewById<MaterialButton>(R.id.btnFacebook)?.setOnClickListener { openUrl("https://www.facebook.com") }
                view.findViewById<MaterialButton>(R.id.btnInstagram)?.setOnClickListener { openUrl("https://www.instagram.com") }

                view.findViewById<MaterialButton>(R.id.btnSendMessage)?.setOnClickListener {
                    val nameInput = view.findViewById<TextInputEditText>(R.id.etName)?.text.toString().trim()
                    if (nameInput.isNotEmpty()) {
                        Toast.makeText(this, "message sent", Toast.LENGTH_SHORT).show()
                        show(R.layout.activity_home_page)
                    } else {
                        Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            R.layout.activity_booking -> {
                val etDate = view.findViewById<TextInputEditText>(R.id.etDate)
                etDate?.setOnClickListener {
                    val c = Calendar.getInstance()
                    DatePickerDialog(this, { _, year, month, day ->
                        etDate.setText("$year-${month + 1}-$day")
                    }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show()
                }

                val btnDecor = view.findViewById<MaterialButton>(R.id.btnDecor)
                val btnCatering = view.findViewById<MaterialButton>(R.id.btnCatering)
                val btnFull = view.findViewById<MaterialButton>(R.id.btnFull)
                val blue = Color.parseColor("#0D2A6E")
                val gold = Color.parseColor("#FFD700")
                val white = Color.parseColor("#F8F9FF")

                val updateServiceHighlight = { service: String ->
                    selectedService = service
                    btnDecor?.backgroundTintList = ColorStateList.valueOf(if (service == "Decor") blue else white)
                    btnDecor?.setTextColor(if (service == "Decor") gold else blue)
                    btnCatering?.backgroundTintList = ColorStateList.valueOf(if (service == "Catering") blue else white)
                    btnCatering?.setTextColor(if (service == "Catering") gold else blue)
                    btnFull?.backgroundTintList = ColorStateList.valueOf(if (service == "Full Event") blue else white)
                    btnFull?.setTextColor(if (service == "Full Event") gold else blue)
                }

                btnDecor?.setOnClickListener { updateServiceHighlight("Decor") }
                btnCatering?.setOnClickListener { updateServiceHighlight("Catering") }
                btnFull?.setOnClickListener { updateServiceHighlight("Full Event") }

                view.findViewById<MaterialButton>(R.id.btnSubmitBooking)?.setOnClickListener {
                    val name = view.findViewById<TextInputEditText>(R.id.etFullName)?.text.toString().trim()
                    val date = etDate?.text.toString().trim()
                    if (name.isEmpty() || date.isEmpty() || selectedService.isEmpty()) {
                        Toast.makeText(this, "Please fill all details and select a service", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "booking submitted", Toast.LENGTH_LONG).show()
                        show(R.layout.activity_home_page)
                    }
                }
            }
            R.layout.activity_settings -> {
                // Toggles
                val switchNotifications = view.findViewById<SwitchMaterial>(R.id.switchNotifications)
                switchNotifications?.isChecked = prefs.getBoolean("notifications", true)
                switchNotifications?.setOnCheckedChangeListener { _, isChecked ->
                    prefs.edit().putBoolean("notifications", isChecked).apply()
                    Toast.makeText(this, if (isChecked) "Notifications On" else "Notifications Off", Toast.LENGTH_SHORT).show()
                }

                val switchDarkMode = view.findViewById<SwitchMaterial>(R.id.switchDarkMode)
                switchDarkMode?.isChecked = prefs.getBoolean("darkMode", false)
                switchDarkMode?.setOnCheckedChangeListener { _, isChecked ->
                    prefs.edit().putBoolean("darkMode", isChecked).apply()
                    AppCompatDelegate.setDefaultNightMode(if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
                }

                val switchEmail = view.findViewById<SwitchMaterial>(R.id.switchEmail)
                switchEmail?.isChecked = prefs.getBoolean("emailUpdates", false)
                switchEmail?.setOnCheckedChangeListener { _, isChecked ->
                    prefs.edit().putBoolean("emailUpdates", isChecked).apply()
                }

                // Preferences 3 to 7 - Opening Information Dialogs
                view.findViewById<MaterialButton>(R.id.btnPref3)?.setOnClickListener {
                    showInfoDialog("Service Area Coverage", "We proudly serve Bothas Hill, Hillcrest, Kloof, Pinetown, Westville, and the Greater Durban region. We bring our mobile studio directly to your venue.")
                }
                view.findViewById<MaterialButton>(R.id.btnPref4)?.setOnClickListener {
                    showInfoDialog("Event Packages", "Current Specials:\n• Lobola Ceremony: R5,500\n• White Wedding: R8,500\n• 10% Discount for early bookings (3 months in advance).")
                }
                view.findViewById<MaterialButton>(R.id.btnPref5)?.setOnClickListener {
                    showInfoDialog("Currency & Payments", "All pricing is in South African Rand (ZAR). We support secure EFT, PayShap, and Cash Deposits.")
                }
                view.findViewById<MaterialButton>(R.id.btnPref6)?.setOnClickListener {
                    showInfoDialog("Business Profile", "Senzakwenzeke (Pty) Ltd. Version 1.0. Est. 2024. 100% Black Female Owned excellence in decor and catering.")
                }
                view.findViewById<MaterialButton>(R.id.btnPref7)?.setOnClickListener {
                    showInfoDialog("Privacy & Terms", "POPIA Compliant. Your data is encrypted. A 50% non-refundable deposit is required to confirm any event date.")
                }

                // Account Save
                val etName = view.findViewById<TextInputEditText>(R.id.etChangeName)
                val etPhone = view.findViewById<TextInputEditText>(R.id.etChangePhone)
                etName?.setText(prefs.getString("userName", ""))
                etPhone?.setText(prefs.getString("userPhone", ""))

                view.findViewById<MaterialButton>(R.id.btnSaveAccount)?.setOnClickListener {
                    val newName = etName?.text.toString().trim()
                    val newPhone = etPhone?.text.toString().trim()
                    if (newName.isNotEmpty()) {
                        prefs.edit().putString("userName", newName).putString("userPhone", newPhone).apply()
                        updateHeaderName()
                        Toast.makeText(this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show()
                    }
                }

                view.findViewById<MaterialButton>(R.id.btnLogout)?.setOnClickListener {
                    Toast.makeText(this, "Logging out...", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, Login::class.java))
                    finish()
                }
            }
        }
    }

    private fun showInfoDialog(title: String, message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }

    private fun openUrl(url: String) {
        try { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url))) }
        catch (e: Exception) { Toast.makeText(this, "Link Error", Toast.LENGTH_SHORT).show() }
    }
}
