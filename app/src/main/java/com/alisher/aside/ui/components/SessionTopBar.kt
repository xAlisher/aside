package com.alisher.aside.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alisher.aside.BuildConfig

/**
 * Top‑bar shown during an active session.
 * Left  → live peer/connection status.
 * Right → Exit action.
 */
@Composable
fun SessionTopBar(
    peerState: PeerState,
    onExit: () -> Unit,
    onDebugCycle: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .statusBarsPadding()
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ) {
            if (BuildConfig.DEBUG) {
                ConnectionStatus(
                    status = peerState,
                    modifier = Modifier.clickable { onDebugCycle() }
                )
            } else {
                ConnectionStatus(peerState)
            }
        }

        Spacer(Modifier.weight(1f))

        Box(
            modifier = Modifier.padding(end = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            ExitButton(onExit)
        }
    }
}
