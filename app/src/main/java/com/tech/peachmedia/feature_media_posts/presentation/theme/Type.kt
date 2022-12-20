package com.tech.peachmedia.feature_media_posts.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tech.peachmedia.R

val dm_sans = FontFamily(
    Font(R.font.dm_sans),
    Font(R.font.dm_sans_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val typography = Typography(
    h1 = TextStyle(
        fontFamily = dm_sans,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        letterSpacing = 0.5.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = dm_sans,
        fontWeight = FontWeight.Bold,
        fontSize = 23.sp,
        letterSpacing = 0.sp
    ),
    body1 = TextStyle(
        fontFamily = dm_sans,
        fontSize = 16.sp,
        letterSpacing = 0.sp
    ),
    body2 = TextStyle(
        fontFamily = dm_sans,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    ),
    caption = TextStyle(
        fontFamily = dm_sans,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    )

)
