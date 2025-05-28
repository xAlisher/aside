package com.alisher.aside.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alisher.aside.ui.theme.AsideTheme
import com.alisher.aside.ui.theme.ConnectionStatusHeight
import com.alisher.aside.ui.theme.ConnectionStatusMarginEnd
import com.alisher.aside.ui.theme.ConnectionStatusPaddingVertical
import com.alisher.aside.ui.theme.ConnectionStatusWidth
import kotlinx.coroutines.delay

enum class PeerState { Offline, Connecting, Connected, Exited }


@Composable
fun ConnectionStatus(status: PeerState, modifier: Modifier = Modifier) {
    val normalColor = when (status) {
        PeerState.Offline -> AsideTheme.colors.grayShadow
        PeerState.Connecting -> AsideTheme.colors.yellowCone
        PeerState.Connected -> AsideTheme.colors.purplePrivate
        PeerState.Exited -> AsideTheme.colors.redAlarm
    }
    val dimColor = when (status) {
        PeerState.Offline -> AsideTheme.colors.grayDust
        PeerState.Connecting -> AsideTheme.colors.mustardPulse
        else -> normalColor
    }
    val textColor = when (status) {
        PeerState.Connecting -> AsideTheme.colors.blackHole
        else -> AsideTheme.colors.whitePure
    }
    val color = remember { Animatable(normalColor) }

    LaunchedEffect(status) {
        color.snapTo(normalColor)
        if (status == PeerState.Offline || status == PeerState.Connecting) {
            while (true) {
                delay(800)
                color.animateTo(dimColor, animationSpec = tween(500, easing = FastOutLinearInEasing))
                delay(1500)
                color.animateTo(normalColor, animationSpec = tween(300, easing = LinearOutSlowInEasing))
            }
        }
    }

    Box(
        modifier = modifier
            .padding(
                top = ConnectionStatusPaddingVertical,
                bottom = ConnectionStatusPaddingVertical,
                end = ConnectionStatusMarginEnd
            )
            .size(ConnectionStatusWidth, ConnectionStatusHeight)
            .clip(RoundedCornerShape(4.dp))
            .background(color.value),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = when (status) {
                PeerState.Offline -> "Peer is offline"
                PeerState.Connecting -> "Connecting…"
                PeerState.Connected -> "Peer connected"
                PeerState.Exited -> "Peer exited"
            },
            color = textColor,
            style = AsideTheme.typography.labelSmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ConnectionStatusPreview() {
    AsideTheme {
        Column {
            ConnectionStatus(PeerState.Offline)
            Spacer(modifier = Modifier.height(8.dp))
            ConnectionStatus(PeerState.Connecting)
            Spacer(modifier = Modifier.height(8.dp))
            ConnectionStatus(PeerState.Connected)
            Spacer(modifier = Modifier.height(8.dp))
            ConnectionStatus(PeerState.Exited)
        }
    }
}
