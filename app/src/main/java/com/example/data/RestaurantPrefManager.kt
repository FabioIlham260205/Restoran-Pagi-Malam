package com.example.data

import android.content.Context
import android.content.SharedPreferences

class RestaurantPrefManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_NAME = "restaurant_prefs"
        private const val KEY_PROFILE_NAME = "profile_name"
        private const val KEY_PROFILE_ADDRESS = "profile_address"
        private const val KEY_PROFILE_DESC = "profile_desc"
        private const val KEY_PROFILE_HOURS = "profile_hours"
        private const val KEY_DARK_MODE = "dark_mode"
        private const val KEY_RATING_PREFIX = "rating_"

        // Defaults
        private const val DEFAULT_NAME = "Restoran Pagi Malam"
        private const val DEFAULT_ADDRESS = "Jl. GubenurSuryo Gang Johar No. 11, Probolinggo"
        private const val DEFAULT_DESC = "Menyajikan cita rasa autentik khas hidangan tradisional Nusantara dengan perpaduan resep warisan dan kenyamanan modern."
        private const val DEFAULT_HOURS = "07:00 - 22:00 WIB"
    }

    fun getProfile(): RestaurantProfile {
        val name = prefs.getString(KEY_PROFILE_NAME, DEFAULT_NAME) ?: DEFAULT_NAME
        val address = prefs.getString(KEY_PROFILE_ADDRESS, DEFAULT_ADDRESS) ?: DEFAULT_ADDRESS
        val desc = prefs.getString(KEY_PROFILE_DESC, DEFAULT_DESC) ?: DEFAULT_DESC
        val hours = prefs.getString(KEY_PROFILE_HOURS, DEFAULT_HOURS) ?: DEFAULT_HOURS
        return RestaurantProfile(name, address, desc, hours)
    }

    fun saveProfile(profile: RestaurantProfile) {
        prefs.edit()
            .putString(KEY_PROFILE_NAME, profile.name)
            .putString(KEY_PROFILE_ADDRESS, profile.address)
            .putString(KEY_PROFILE_DESC, profile.description)
            .putString(KEY_PROFILE_HOURS, profile.openingHours)
            .apply()
    }

    fun isDarkMode(systemDefault: Boolean): Boolean {
        return prefs.getBoolean(KEY_DARK_MODE, systemDefault)
    }

    fun setDarkMode(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_DARK_MODE, enabled).apply()
    }

    fun getMenuRating(menuId: String, defaultValue: Float): Float {
        return prefs.getFloat(KEY_RATING_PREFIX + menuId, defaultValue)
    }

    fun saveMenuRating(menuId: String, rating: Float) {
        prefs.edit().putFloat(KEY_RATING_PREFIX + menuId, rating).apply()
    }
}
