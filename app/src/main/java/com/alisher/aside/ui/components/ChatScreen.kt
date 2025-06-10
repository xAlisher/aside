package com.alisher.aside.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
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
    onSend: () -> Unit,
    onCycle: () -> Unit,
    onExit: () -> Unit,
    messages: List<ChatMessage>,
    listState: MessageListState = rememberMessageListState()
) {
    val keyboard = LocalSoftwareKeyboardController.current

    if (peerState == PeerState.Exited) {
        LaunchedEffect(peerState) { keyboard?.hide() }
    }

    LaunchedEffect(messages.size) {
        if (listState.lazyListState.firstVisibleItemIndex == 0) {
            listState.scrollToBottom()
        }
    }

    Scaffold(
        topBar = {
            SessionTopBar(
                peerState   = peerState,
                onExit      = onExit,
                onDebugCycle = onCycle
            )
        },
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
                    onSend        = onSend,
                    modifier      = Modifier
                        // clears navigation-bar height (3-button mode)
                        .windowInsetsPadding(WindowInsets.navigationBars)
                        // clears keyboard + suggestion strip
                        .windowInsetsPadding(WindowInsets.ime)
                )
            }
        }
    ) { padding ->
        MessageList(
            messages = messages,
            state = listState,
            modifier = Modifier.padding(padding)
        )
    }
}
