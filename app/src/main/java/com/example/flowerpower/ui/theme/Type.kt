package com.example.flowerpower.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.flowerpower.R

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

val jambo = FontFamily(Font(R.font.jambo))
val robotoMedium = FontFamily(Font(R.font.roboto_medium))
val vanillaCake = FontFamily(Font(R.font.vanilla_cake))