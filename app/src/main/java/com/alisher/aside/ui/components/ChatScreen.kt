package com.alisher.aside.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.ime
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

@Composable
fun ChatScreen(
    peerState: PeerState,
    draft: String,
    onDraftChange: (String) -> Unit,
    onExit: () -> Unit
) {
    val keyboard = LocalSoftwareKeyboardController.current

    if (peerState == PeerState.Exited) {
        LaunchedEffect(peerState) { keyboard?.hide() }
    }

    Scaffold(
        topBar = { SessionTopBar(peerState, onExit) },
        bottomBar = {
            if (peerState != PeerState.Exited) {
                val buttonType = when (peerState) {
                    PeerState.Connected  -> ButtonType.Send
                    PeerState.Offline,
                    PeerState.Connecting -> ButtonType.Queue
                    PeerState.Exited     -> ButtonType.Send
                }

                InputField(
                    text          = draft,
                    onValueChange = onDraftChange,
                    buttonType    = buttonType,
                    modifier      = Modifier
                        // clears navigation-bar height (3-button mode)
                        .windowInsetsPadding(WindowInsets.navigationBars)
                        // clears keyboard + suggestion strip
                        .windowInsetsPadding(WindowInsets.ime)
                )
            }
        }
    ) { /* messages go here later */ }
}
