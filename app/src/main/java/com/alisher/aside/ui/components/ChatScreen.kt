package com.alisher.aside.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alisher.aside.ui.theme.AsideTheme

/** Data class representing a chat message. */
data class ChatMessage(
    val type: MessageType,
    val text: String,
    val status: MessageStatus? = null
)

/**
 * Full chat screen with a list of messages, input field and send/queue button.
 *
 * @param messages       Conversation history to display.
 * @param peerState      Current peer connection state for the top bar.
 * @param onSendMessage  Called when user submits a message.
 * @param onExit         Called when exit action is tapped.
 */
@Composable
fun ChatScreen(
    messages: List<ChatMessage>,
    peerState: PeerState,
    onSendMessage: (String) -> Unit,
    onExit: () -> Unit,
    modifier: Modifier = Modifier
) {
    var input by remember { mutableStateOf("") }

    Column(modifier.fillMaxSize()) {
        SessionTopBar(peerState = peerState, onExit = onExit)

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(AsideTheme.colors.blackHole)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(messages) { msg ->
                Message(
                    type = msg.type,
                    text = msg.text,
                    messageStatusState = msg.status
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(AsideTheme.colors.blackHole)
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            InputField(
                text = input,
                onValueChange = { input = it },
                modifier = Modifier.weight(1f)
            )

            Spacer(Modifier.width(16.dp))

            val buttonState = if (input.isEmpty()) ButtonState.Disabled else ButtonState.Default
            val buttonType = if (peerState == PeerState.Connected) ButtonType.Send else ButtonType.Queue
            SendQueueButton(
                type = buttonType,
                state = buttonState,
                onClick = {
                    if (input.isNotBlank()) {
                        onSendMessage(input)
                        input = ""
                    }
                },
                modifier = Modifier.align(Alignment.Bottom)
            )
        }
    }
}
