package com.alisher.aside.ui.debug

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alisher.aside.ui.components.*
import com.alisher.aside.ui.theme.AsideTheme

@Preview(showBackground = true, backgroundColor = 0xFF202020)
@Composable
fun ChatScreenPreview() {
    AsideTheme {
        val online = remember { mutableStateOf(true) }

        Column(Modifier.fillMaxSize()) {
            ChatScreen(
                peerState = if (online.value) PeerState.Connected else PeerState.Offline,
                onExit = { }
            )

            Row(Modifier.padding(16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                SendQueueButton(
                    type = ButtonType.Send,
                    state = ButtonState.Default,
                    onClick = { online.value = true }
                )
                SendQueueButton(
                    type = ButtonType.Queue,
                    state = ButtonState.Default,
                    onClick = { online.value = false }
                )
            }
        }
    }
}
