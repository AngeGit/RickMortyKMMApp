package com.angiedev.rickmortyapp.ui.core.resources

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object AppTypography {
    val regularXL = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 30.sp,
    )

    val semiboldL = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold
    )

    val regularM = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontSize = 20.sp,
    )
    val regularS = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontSize = 18.sp,
    )

    val regularXS = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontSize = 16.sp,
    )

    val regularXXS = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontSize = 12.sp,
    )
    val regularBoldS = regularS.copy(fontWeight = FontWeight.Bold)
    val regularBoldXS = regularXS.copy(fontWeight = FontWeight.Bold)

}