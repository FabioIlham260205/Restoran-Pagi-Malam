package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun StarRating(
    rating: Float,
    onRatingSelected: ((Float) -> Unit)? = null,
    modifier: Modifier = Modifier,
    starSize: Dp = 32.dp,
    gap: Dp = 4.dp
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val interactive = onRatingSelected != null

        for (i in 1..5) {
            val isSelected = i <= rating
            val starIcon = if (isSelected) Icons.Filled.Star else Icons.Outlined.StarBorder
            val activeColor = Color(0xFFFFB300) // Beautiful Warm Amber Gold
            val inactiveColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)

            val animatedColor by animateColorAsState(
                targetValue = if (isSelected) activeColor else inactiveColor,
                animationSpec = spring(stiffness = Spring.StiffnessLow),
                label = "star_color"
            )

            val animatedScale by animateFloatAsState(
                targetValue = if (isSelected) 1.2f else 1.0f,
                animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
                label = "star_scale"
            )

            Icon(
                imageVector = starIcon,
                contentDescription = "Rating Star $i of 5",
                tint = animatedColor,
                modifier = Modifier
                    .size(starSize)
                    .padding(horizontal = gap / 2)
                    .scale(if (interactive) animatedScale else 1f)
                    .then(
                        if (interactive && onRatingSelected != null) {
                            Modifier
                                .testTag("star_$i")
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null // Custom clean animation handling
                                ) {
                                    onRatingSelected(i.toFloat())
                                }
                        } else Modifier
                    )
            )
        }
    }
}
