package com.myplanner.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


var textColor = black
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */

val RegularN10 = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 10.sp,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)

val RegularN12 = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)

val MediumN10 = TextStyle(
    fontWeight = FontWeight.Medium,
    fontSize = 10.sp,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)

val MediumN12 = TextStyle(
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)

val MediumN14 = TextStyle(
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)

val BoldN10 = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 10.sp,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)


val BoldN12 = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 12.sp,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)

val BoldN14 = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)

val BoldN20 = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)

val BlackN10 = TextStyle(
    fontWeight = FontWeight.Black,
    fontSize = 10.sp,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)

val BlackN12 = TextStyle(
    fontWeight = FontWeight.Black,
    fontSize = 12.sp,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)


val MPText = TextStyle(
    fontWeight = FontWeight.Black,
    fontSize = 12.sp,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)