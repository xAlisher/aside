package com.alisher.aside.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val AsideColorScheme = darkColorScheme(
    background = BlackHole,
    surface = GrayGraphene,
    primary = PurplePrivate,
    secondary = YellowCone,
    tertiary = RedAlarm,
    onPrimary = WhitePure,
    onSecondary = BlackHole,
    onTertiary = WhitePure,
    onBackground = WhitePure,
    onSurface = WhitePure
)

data class AsideColors(
    val blackHole: Color = BlackHole,
    val grayCharcoal: Color = GrayCharcoal,
    val grayGraphene: Color = GrayGraphene,
    val grayShadow: Color = GrayShadow,
    val grayDust: Color = GrayDust,
    val whitePure: Color = WhitePure,
    val yellowCone: Color = YellowCone,
    val mustardPulse: Color = MustardPulse,
    val purplePrivate: Color = PurplePrivate,
    val redAlarm: Color = RedAlarm
)

private val LocalAsideColors = staticCompositionLocalOf { AsideColors() }

object AsideTheme {
    val colors: AsideColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAsideColors.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = AsideTypography
}

@Composable
fun AsideTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalAsideColors provides AsideColors()) {
        MaterialTheme(
            colorScheme = AsideColorScheme,
            typography = AsideTypography,
            content = content
        )
    }
}

val ConnectionStatusHeight = 32.dp
val ConnectionStatusWidth = 160.dp
val ConnectionStatusMarginEnd = 20.dp
val ConnectionStatusPaddingVertical = 8.dp
