package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.data.MenuItem
import com.example.data.RestaurantPrefManager
import com.example.data.RestaurantProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Calendar

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val prefManager = RestaurantPrefManager(application)

    // Dark layout state flow
    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode: StateFlow<Boolean> = _isDarkMode.asStateFlow()

    // Profile state flow
    private val _restaurantProfile = MutableStateFlow(prefManager.getProfile())
    val restaurantProfile: StateFlow<RestaurantProfile> = _restaurantProfile.asStateFlow()

    // Menu list state flow
    private val _menuItems = MutableStateFlow<List<MenuItem>>(emptyList())
    val menuItems: StateFlow<List<MenuItem>> = _menuItems.asStateFlow()

    init {
        // Initialize dark mode from system default logic or saved settings
        _isDarkMode.value = prefManager.isDarkMode(false)
        loadMenuItems()
    }

    private fun loadMenuItems() {
        val baseItems = listOf(
            MenuItem(
                id = "nasi_goreng",
                name = "Nasi Goreng Spesial",
                price = 25000.0,
                category = "Makanan",
                description = "Nasi goreng harum berselimut bumbu rempah Jawa klasik, disajikan hangat bersama telur mata sapi dadar, suwiran daging ayam gurih, acar segar, dan kerupuk udang renyah.",
                imageUrl = "https://images.unsplash.com/photo-1603133872878-684f208fb84b?auto=format&fit=crop&q=80&w=400",
                rating = 4.8f
            ),
            MenuItem(
                id = "sate_ayam",
                name = "Sate Ayam Madura",
                price = 30000.0,
                category = "Makanan",
                description = "Sate daging dada ayam empuk pilihan dipanggang di atas bara arang batok kelapa asli, disiram saus bumbu kacang kental gurih manis khas Madura berserta taburan bawang goreng.",
                imageUrl = "https://images.unsplash.com/photo-1529193591184-b1d58069ecdd?auto=format&fit=crop&q=80&w=400",
                rating = 4.7f
            ),
            MenuItem(
                id = "rendang_sapi",
                name = "Rendang Daging Sapi",
                price = 35000.0,
                category = "Makanan",
                description = "Potongan daging sapi segar empuk yang dimasak perlahan bersama dengan parutan kelapa asli dan ramuan rempah-rempah alami pilihan hingga bumbu meresap hitam sempurna.",
                imageUrl = "https://images.unsplash.com/photo-1644342398505-f377038148b3?auto=format&fit=crop&q=80&w=400",
                rating = 4.9f
            ),
            MenuItem(
                id = "ayam_penyet",
                name = "Ayam Penyet Cabe Ijo",
                price = 28000.0,
                category = "Makanan",
                description = "Ayam ungkep bumbu kuning tradisional digoreng renyah keemasan, lalu dipenyet dengan ulekan lombok cabai hijau segar gurih tingkat kepedasan mantap.",
                imageUrl = "https://images.unsplash.com/photo-1626082927389-6cd097cdc6ec?auto=format&fit=crop&q=80&w=400",
                rating = 4.6f
            ),
            MenuItem(
                id = "es_campur",
                name = "Es Campur Nusantara",
                price = 15000.0,
                category = "Minuman",
                description = "Es serut manis nan segar berisi potongan cincau hitam kenyal, serutan daging kelapa muda, kolang-kaling empuk, alpukat gurih, susu kental manis, dan sirup merah asli.",
                imageUrl = "https://images.unsplash.com/photo-1601300977461-127e997f0a99?auto=format&fit=crop&q=80&w=400",
                rating = 4.6f
            ),
            MenuItem(
                id = "es_jeruk",
                name = "Es Jeruk Peras Murni",
                price = 12000.0,
                category = "Minuman",
                description = "Perasan murni dari jeruk peras hijau segar pilihan tanpa pemanis buatan, disajikan dingin segar melepas dahaga.",
                imageUrl = "https://images.unsplash.com/photo-1613478223719-2ab802602423?auto=format&fit=crop&q=80&w=400",
                rating = 4.5f
            ),
            MenuItem(
                id = "kopi_tubruk",
                name = "Kopi Susu Tubruk",
                price = 14000.0,
                category = "Minuman",
                description = "Kopi robusta premium giling sedang diseduh langsung dengan air mendidih mendalam, dicampur susu kental manis menyajikan aroma harum pekat berkarakter khas barista lokal.",
                imageUrl = "https://images.unsplash.com/photo-1541167760496-162955ed8a9f?auto=format&fit=crop&q=80&w=400",
                rating = 4.7f
            )
        )

        // Enrich rating based on user-saved stars in SharedPreferences
        val list = baseItems.map { item ->
            val savedRating = prefManager.getMenuRating(item.id, item.rating)
            item.copy(rating = savedRating)
        }
        _menuItems.value = list
    }

    fun updateProfile(name: String, address: String, description: String, openingHours: String) {
        val updated = RestaurantProfile(name, address, description, openingHours)
        _restaurantProfile.value = updated
        prefManager.saveProfile(updated)
    }

    fun toggleDarkMode() {
        val newValue = !_isDarkMode.value
        _isDarkMode.value = newValue
        prefManager.setDarkMode(newValue)
    }

    fun setRating(menuId: String, newRating: Float) {
        prefManager.saveMenuRating(menuId, newRating)
        // Refresh menu items
        loadMenuItems()
    }

    fun getGreetingText(): String {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return when (hour) {
            in 4..10 -> "Selamat Pagi, Penikmat Seni Kuliner! ☀️"
            in 11..14 -> "Selamat Siang, Pecinta Cita Rasa! 🌤️"
            in 15..17 -> "Selamat Sore, Kawan Bistro! 🌇"
            else -> "Selamat Malam, Nikmati Suasana Hangat! 🌙"
        }
    }
}
