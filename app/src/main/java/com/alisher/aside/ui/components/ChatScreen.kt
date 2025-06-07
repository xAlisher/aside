package com.alisher.aside.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.ime
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ChatScreen(
    peerState: PeerState,
    draft: String,
    onDraftChange: (String) -> Unit,
    onExit: () -> Unit
) {
    Scaffold(
        topBar = { SessionTopBar(peerState, onExit) },
        bottomBar = {
            InputField(
                text          = draft,
                onValueChange = onDraftChange,
                modifier      = Modifier
                    // clears navigation-bar height (3-button mode)
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    // clears keyboard + suggestion strip
                    .windowInsetsPadding(WindowInsets.ime)
            )
        }
    ) { /* messages go here later */ }
}
