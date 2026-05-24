package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun FoodIllustration(
    id: String,
    modifier: Modifier = Modifier
) {
    // Choose custom gradient colors matching the specific menu id
    val gradientBrushes = when (id) {
        "nasi_goreng" -> Brush.verticalGradient(listOf(Color(0xFFF9A825), Color(0xFFEF6C00))) // Orange/Gold
        "sate_ayam" -> Brush.verticalGradient(listOf(Color(0xFFFFB300), Color(0xFF8D6E63)))  // Honey/Brown
        "rendang_sapi" -> Brush.verticalGradient(listOf(Color(0xFF8D6E63), Color(0xFF3E2723))) // Beef Cocoa
        "ayam_penyet" -> Brush.verticalGradient(listOf(Color(0xFFE53935), Color(0xFFB71C1C)))  // Hot Chili
        "es_campur" -> Brush.verticalGradient(listOf(Color(0xFFFF8A80), Color(0xFFE91E63)))    // Sweet Pink
        "es_jeruk" -> Brush.verticalGradient(listOf(Color(0xFFFFD54F), Color(0xFFF57C00)))    // Fresh Orange
        "kopi_tubruk" -> Brush.verticalGradient(listOf(Color(0xFF5D4037), Color(0xFF1A0C00)))  // Pure Espresso
        else -> Brush.verticalGradient(listOf(Color(0xFF4DB6AC), Color(0xFF00796B)))          // Teal Default
    }

    Box(
        modifier = modifier
            .background(gradientBrushes)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height
            val center = Offset(w / 2f, h / 2f)

            when (id) {
                "nasi_goreng" -> {
                    // Plate base
                    drawCircle(
                        color = Color.White.copy(alpha = 0.2f),
                        radius = w * 0.4f,
                        center = center
                    )
                    // Egg white
                    drawCircle(
                        color = Color.White.copy(alpha = 0.85f),
                        radius = w * 0.16f,
                        center = Offset(w * 0.45f, h * 0.45f)
                    )
                    // Yolk
                    drawCircle(
                        color = Color(0xFFFFC107),
                        radius = w * 0.08f,
                        center = Offset(w * 0.43f, h * 0.44f)
                    )
                    // Rice grains hints
                    drawCircle(color = Color.White.copy(alpha = 0.4f), radius = 6f, center = Offset(w * 0.35f, h * 0.62f))
                    drawCircle(color = Color.White.copy(alpha = 0.4f), radius = 5f, center = Offset(w * 0.58f, h * 0.58f))
                    drawCircle(color = Color.White.copy(alpha = 0.4f), radius = 6f, center = Offset(w * 0.62f, h * 0.38f))
                }
                "sate_ayam" -> {
                    // Wooden skewer stick
                    drawLine(
                        color = Color(0xFFD7CCC8),
                        start = Offset(w * 0.2f, h * 0.8f),
                        end = Offset(w * 0.8f, h * 0.2f),
                        strokeWidth = 10f
                    )
                    // Meat pieces as rounded corners
                    drawRoundRect(
                        color = Color(0xFF5D4037),
                        topLeft = Offset(w * 0.35f, h * 0.5f),
                        size = Size(w * 0.18f, h * 0.14f),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(15f, 15f)
                    )
                    drawRoundRect(
                        color = Color(0xFF4E342E),
                        topLeft = Offset(w * 0.5f, h * 0.35f),
                        size = Size(w * 0.18f, h * 0.14f),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(15f, 15f)
                    )
                    drawRoundRect(
                        color = Color(0xFF3E2723),
                        topLeft = Offset(w * 0.62f, h * 0.22f),
                        size = Size(w * 0.16f, h * 0.12f),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(15f, 15f)
                    )
                }
                "rendang_sapi" -> {
                    // Elegant traditional layout representation
                    val path = Path().apply {
                        moveTo(w * 0.3f, h * 0.5f)
                        lineTo(w * 0.45f, h * 0.32f)
                        lineTo(w * 0.7f, h * 0.4f)
                        lineTo(w * 0.65f, h * 0.65f)
                        lineTo(w * 0.4f, h * 0.7f)
                        close()
                    }
                    drawPath(path, color = Color(0xFF2D1500))
                    // Coconut gravy sprinkles
                    drawCircle(color = Color(0xFFD7CCC8).copy(alpha = 0.6f), radius = 8f, center = Offset(w * 0.42f, h * 0.45f))
                    drawCircle(color = Color(0xFFD7CCC8).copy(alpha = 0.6f), radius = 10f, center = Offset(w * 0.55f, h * 0.55f))
                    drawCircle(color = Color(0xFFD7CCC8).copy(alpha = 0.5f), radius = 6f, center = Offset(w * 0.62f, h * 0.42f))
                }
                "ayam_penyet" -> {
                    // Chicken drumstick representation
                    val path = Path().apply {
                        moveTo(w * 0.3f, h * 0.65f)
                        quadraticTo(w * 0.2f, h * 0.75f, w * 0.25f, h * 0.8f)
                        quadraticTo(w * 0.35f, h * 0.75f, w * 0.45f, h * 0.55f)
                        quadraticTo(w * 0.75f, h * 0.65f, w * 0.8f, h * 0.45f)
                        quadraticTo(w * 0.85f, h * 0.25f, w * 0.65f, h * 0.25f)
                        quadraticTo(w * 0.4f, h * 0.35f, w * 0.3f, h * 0.65f)
                    }
                    drawPath(path, color = Color(0xFF8D6E63))
                    // Red chili dots (Sambal Ijo / Pedas)
                    drawCircle(color = Color(0xFFC62828), radius = 9f, center = Offset(w * 0.55f, h * 0.36f))
                    drawCircle(color = Color(0xFF2E7D32), radius = 11f, center = Offset(w * 0.62f, h * 0.45f))
                    drawCircle(color = Color(0xFFC62828), radius = 8f, center = Offset(w * 0.48f, h * 0.48f))
                }
                "es_campur" -> {
                    // Sweet syrup glass base outline
                    val glassPath = Path().apply {
                        moveTo(w * 0.25f, h * 0.25f)
                        lineTo(w * 0.75f, h * 0.25f)
                        quadraticTo(w * 0.7f, h * 0.65f, w * 0.55f, h * 0.75f)
                        lineTo(w * 0.55f, h * 0.85f)
                        lineTo(w * 0.65f, h * 0.85f)
                        lineTo(w * 0.65f, h * 0.9f)
                        lineTo(w * 0.35f, h * 0.9f)
                        lineTo(w * 0.35f, h * 0.85f)
                        lineTo(w * 0.45f, h * 0.85f)
                        lineTo(w * 0.45f, h * 0.75f)
                        quadraticTo(w * 0.3f, h * 0.65f, w * 0.25f, h * 0.25f)
                    }
                    drawPath(glassPath, color = Color.White.copy(alpha = 0.3f))
                    // Fill contents
                    drawCircle(color = Color(0xFFFF4081), radius = w * 0.18f, center = Offset(w * 0.5f, h * 0.42f))
                    drawCircle(color = Color(0xFFFFEB3B), radius = w * 0.08f, center = Offset(w * 0.42f, h * 0.38f)) // Pineapple / avocado dot
                    drawCircle(color = Color(0xFF4CAF50), radius = w * 0.07f, center = Offset(w * 0.58f, h * 0.45f)) // Jelly green dot
                }
                "es_jeruk" -> {
                    // Tangerine slice
                    drawCircle(
                        color = Color.White.copy(alpha = 0.3f),
                        radius = w * 0.38f,
                        center = center
                    )
                    drawCircle(
                        color = Color(0xFFFF9800),
                        radius = w * 0.34f,
                        center = center
                    )
                    // Segments
                    for (i in 0 until 8) {
                        val angle = (i * 45) * Math.PI / 180.0
                        drawLine(
                            color = Color(0xFFFFD54F),
                            start = center,
                            end = Offset(
                                (center.x + (w * 0.33) * Math.cos(angle)).toFloat(),
                                (center.y + (w * 0.33) * Math.sin(angle)).toFloat()
                            ),
                            strokeWidth = 6f
                        )
                    }
                    // Outer rim outline
                    drawCircle(
                        color = Color(0xFFF57C00),
                        radius = w * 0.34f,
                        center = center,
                        style = Stroke(width = 8f)
                    )
                }
                "kopi_tubruk" -> {
                    // Coffee cup outline
                    drawRoundRect(
                        color = Color(0xFFD7CCC8).copy(alpha = 0.8f),
                        topLeft = Offset(w * 0.3f, h * 0.35f),
                        size = Size(w * 0.4f, h * 0.44f),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(20f, 20f)
                    )
                    // Glass Handle
                    val handlePath = Path().apply {
                        moveTo(w * 0.7f, h * 0.42f)
                        quadraticTo(w * 0.85f, h * 0.46f, w * 0.85f, h * 0.57f)
                        quadraticTo(w * 0.82f, h * 0.68f, w * 0.7f, h * 0.72f)
                    }
                    drawPath(
                        path = handlePath,
                        color = Color(0xFFD7CCC8).copy(alpha = 0.8f),
                        style = Stroke(width = 10f)
                    )
                    // Coffee liquid level
                    drawRect(
                        color = Color(0xFF3E2723),
                        topLeft = Offset(w * 0.32f, h * 0.42f),
                        size = Size(w * 0.36f, h * 0.34f)
                    )
                    // Steam wave lines
                    drawLine(
                        color = Color.White.copy(alpha = 0.5f),
                        start = Offset(w * 0.42f, h * 0.28f),
                        end = Offset(w * 0.45f, h * 0.16f),
                        strokeWidth = 5f
                    )
                    drawLine(
                        color = Color.White.copy(alpha = 0.5f),
                        start = Offset(w * 0.52f, h * 0.26f),
                        end = Offset(w * 0.55f, h * 0.14f),
                        strokeWidth = 5f
                    )
                }
            }
        }
    }
}
