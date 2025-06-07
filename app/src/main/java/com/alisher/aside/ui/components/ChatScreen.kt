package com.alisher.aside.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.navigationBars
import com.alisher.aside.ui.theme.AsideTheme

@Composable
fun ChatScreen(
    peerState: PeerState,
    onExit: () -> Unit,
    modifier: Modifier = Modifier
) {
    var input by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AsideTheme.colors.blackHole)
    ) {
        SessionTopBar(peerState = peerState, onExit = onExit)

        Box(modifier = Modifier.weight(1f)) {
            // TODO: Replace with LazyColumn for chat messages
        }

        val buttonType = if (peerState == PeerState.Connected) {
            ButtonType.Send
        } else {
            ButtonType.Queue
        }

        InputField(
            text = input,
            onValueChange = { input = it },
            buttonType = buttonType,
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.navigationBars)
        )
    }
}
