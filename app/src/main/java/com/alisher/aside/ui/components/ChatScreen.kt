package com.alisher.aside.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.weight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alisher.aside.ui.theme.AsideTheme
import com.alisher.aside.ui.components.InputField
import com.alisher.aside.ui.components.SessionTopBar
import com.alisher.aside.ui.components.PeerState

/**
 * Minimal chat screen used during early development.
 * It shows the session top bar and an input field at the bottom.
 * Message list and send logic will be added later.
 *
 * @param peerState Current peer connection state for the top bar.
 * @param onExit    Called when exit action is tapped.
 */
@Composable
fun ChatScreen(
    peerState: PeerState,
    onExit: () -> Unit,
    modifier: Modifier = Modifier
) {
    var input by remember { mutableStateOf("") }

    Column(modifier.fillMaxSize().background(AsideTheme.colors.blackHole)) {
        SessionTopBar(peerState = peerState, onExit = onExit)

        Spacer(modifier = Modifier.weight(1f))

        InputField(
            text = input,
            onValueChange = { input = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
        )
    }
}
