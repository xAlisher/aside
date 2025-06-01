package com.alisher.aside.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily  // <-- import Roboto
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/*────────────────────  SIZE TOKENS  ────────────────────*/

private val DisplayMd = 24.sp   // large, headline‐style
private val BodyMd    = 16.sp   // default body copy
private val MsgMd     = 16.sp   // chat / message bubbles
private val TagMd     = 12.sp   // small labels, tags, timestamps, etc.

/*────────────────────  PUBLIC TEXT STYLES  ──────────────*/

val Body = TextStyle(
    fontFamily = FontFamily.Default, // Roboto
    fontSize   = BodyMd,
    fontWeight = FontWeight.Normal
)

val Messages = TextStyle(
    fontFamily = FontFamily.Default, // Roboto
    fontSize   = MsgMd,
    fontWeight = FontWeight.Normal,
    lineHeight = 24.sp
)

val Tag = TextStyle(
    fontFamily = FontFamily.Default, // Roboto
    fontSize   = TagMd,
    fontWeight = FontWeight.Normal
)

/*────────────────────  MATERIAL 3 TYPOGRAPHY  ───────────*/

val AsideTypography = Typography(
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default, // Roboto
        fontSize   = DisplayMd,
        fontWeight = FontWeight.Bold
    ),

    bodyLarge  = Body,      // Roboto, 16sp
    bodyMedium = Messages,  // Roboto, 16sp / lineHeight 24sp
    labelSmall = Tag        // Roboto, 12sp
)
