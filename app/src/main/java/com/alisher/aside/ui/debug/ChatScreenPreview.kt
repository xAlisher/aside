package com.alisher.aside.ui.debug

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.alisher.aside.ui.components.*
import com.alisher.aside.ui.layout.LayoutWrapper
import com.alisher.aside.ui.theme.AsideTheme

@Preview(name = "ChatScreenPreview", showBackground = true, backgroundColor = 0xFF202020)
@Composable
fun ChatScreenPreview() {
    AsideTheme {
        LayoutWrapper {
            var draft by remember { mutableStateOf("") }
            val messages = remember {
                List(40) { idx ->
                    val type = if (idx % 2 == 0) MessageType.In else MessageType.Out
                    ChatMessage(type = type, text = "Message $idx")
                }
            }

            ChatScreen(
                peerState     = PeerState.Connected,
                draft         = draft,
                onDraftChange = { draft = it },
                onExit        = {},
                messages      = messages
            )
        }
    }
}
