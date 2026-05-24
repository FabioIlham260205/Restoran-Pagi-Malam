package com.example.data

data class MenuItem(
    val id: String,
    val name: String,
    val price: Double,
    val category: String, // "Makanan" (Food) or "Minuman" (Drink)
    val description: String,
    val imageUrl: String, // URL to a beautiful food/drink image
    val rating: Float = 4.5f
)
