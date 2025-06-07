package com.alisher.aside.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.alisher.aside.ui.theme.AsideTheme

@Composable
fun ChatScreen(
    peerState: PeerState,
    onExit: () -> Unit,
    modifier: Modifier = Modifier
) {
    var input by remember { mutableStateOf("") }

    val buttonType = if (peerState == PeerState.Connected) {
        ButtonType.Send
    } else {
        ButtonType.Queue
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(AsideTheme.colors.blackHole),
        topBar = { SessionTopBar(peerState = peerState, onExit = onExit) },
        bottomBar = {
            InputField(
                text = input,
                onValueChange = { input = it },
                buttonType = buttonType,
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .windowInsetsPadding(WindowInsets.ime)
            )
        }
    ) { padding ->
        Box(Modifier
            .padding(padding)
            .fillMaxSize()) {
            // TODO: Replace with LazyColumn for chat messages
        }
    }
}
