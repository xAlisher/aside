package com.alisher.aside.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/*────────────────────  SIZE TOKENS  ────────────────────*/

private val DisplayMd = 24.sp   // large, headline-style
private val BodyMd    = 16.sp   // default paragraph
private val MsgMd     = 16.sp   // chat / message bubbles (same size, tweaked lineHeight)

/*────────────────────  PUBLIC TEXT STYLES  ──────────────*/

val Body = TextStyle(
    fontSize   = BodyMd,
    fontWeight = FontWeight.Normal
)

val Messages = TextStyle(
    fontSize   = MsgMd,
    fontWeight = FontWeight.Normal,
    lineHeight = 24.sp          // extra breathing room for multi-line chat text
)

val Tag = TextStyle(
    fontSize   = 12.sp,
    fontWeight = FontWeight.Normal
)

/*────────────────────  MATERIAL 3 TYPOGRAPHY  ───────────*/

val AsideTypography = Typography(
    headlineMedium = TextStyle(                 // e.g. “Let’s step aside”
        fontSize   = DisplayMd,
        fontWeight = FontWeight.Bold
    ),

    bodyLarge  = Body,      // general body copy
    bodyMedium = Messages,  // chat / message text
    labelSmall = Tag        // small labels, tags, timestamps, etc.
)

/*  Usage examples  ────────────────────────────────────────

Text("Regular paragraph", style = MaterialTheme.typography.bodyLarge)

Text("Chat message", style = MaterialTheme.typography.bodyMedium)

Text("System toast directly", style = Messages)

*/
