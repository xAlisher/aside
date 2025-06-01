package com.alisher.aside.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alisher.aside.ui.theme.*
import kotlinx.coroutines.delay

/* ---------- public state enum ---------- */
enum class PeerState { Offline, Connecting, Connected, Exited }

/* ---------- component ---------- */
@Composable
fun ConnectionStatus(
    status: PeerState,
    modifier: Modifier = Modifier
) {
    /* colours ------------------------------------------------------------ */
    val baseDot: Color = when (status) {
        PeerState.Offline    -> AsideTheme.colors.grayShadow
        PeerState.Connecting -> AsideTheme.colors.yellowCone
        PeerState.Connected  -> AsideTheme.colors.purplePrivate
        PeerState.Exited     -> AsideTheme.colors.redAlarm
    }
    val dimDot: Color = when (status) {
        PeerState.Offline    -> AsideTheme.colors.grayDust
        PeerState.Connecting -> AsideTheme.colors.mustardPulse
        else                 -> baseDot               // connected / exited stay solid
    }
    val textCol: Color = baseDot                      // ⬅️ label uses same colour as dot

    /* single Animatable alpha drives dot *and* label -------------------- */
    val alpha = remember { Animatable(1f) }

    LaunchedEffect(status) {
        alpha.snapTo(1f)
        if (status == PeerState.Offline || status == PeerState.Connecting) {
            while (true) {
                delay(2000)                                      // ← bright ON 2 s
                alpha.animateTo(
                    0.4f,
                    tween(1000, easing = FastOutLinearInEasing)  // fade-out 1 s
                )
                delay(400)                                       // optional dim hold
                alpha.animateTo(
                    1f,
                    tween(600,  easing = LinearOutSlowInEasing)  // fade-in 0.6 s
                )
            }
        }
    }

    /* UI ---------------------------------------------------------------- */
    Row(
        modifier = modifier
            .size(ConnectionStatusWidth, ConnectionStatusHeight)
            .padding(
                start  = 20.dp,                         // fixed 20-px gap before the dot
                top    = ConnectionStatusPaddingVertical,
                bottom = ConnectionStatusPaddingVertical
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        /* coloured status dot */
        Box(
            Modifier
                .size(12.dp)
                .clip(CircleShape)
                .alpha(alpha.value)
                .background(baseDot.copy(alpha = alpha.value))
        )

        Spacer(Modifier.width(12.dp))

        /* label --------------------------------------------------------- */
        Text(
            text  = when (status) {
                PeerState.Offline    -> "Peer is offline"
                PeerState.Connecting -> "Connecting…"
                PeerState.Connected  -> "Peer connected"
                PeerState.Exited     -> "Peer exited"
            },
            color = textCol.copy(alpha = alpha.value),
            style = AsideTheme.typography.bodyLarge        // 16 sp Body

        )
    }
}

/* ---------- design-time preview ---------- */
@Preview(showBackground = true, backgroundColor = 0xFF202020)
@Composable
fun ConnectionStatusPreview() {
    AsideTheme {
        Column {
            ConnectionStatus(PeerState.Offline)
            Spacer(Modifier.height(12.dp))
            ConnectionStatus(PeerState.Connecting)
            Spacer(Modifier.height(12.dp))
            ConnectionStatus(PeerState.Connected)
            Spacer(Modifier.height(12.dp))
            ConnectionStatus(PeerState.Exited)
        }
    }
}
