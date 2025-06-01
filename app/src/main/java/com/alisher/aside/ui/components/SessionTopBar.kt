package com.alisher.aside.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alisher.aside.ui.theme.AsideTheme

@Composable
fun SessionTopBar(
    peerState: PeerState,
    onExit: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(AsideTheme.colors.blackHole)
            .padding(horizontal = 20.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ConnectionStatus(peerState)
        Spacer(modifier = Modifier.weight(1f))
        ExitButton(onExit)
    }
}
