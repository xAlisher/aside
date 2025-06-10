package com.alisher.aside.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/** Simple message model used by MessageList */
data class ChatMessage(
    val type: MessageType,
    val text: String,
    val status: MessageStatus? = null
)

class MessageListState(val lazyListState: LazyListState) {
    suspend fun scrollToBottom() {
        lazyListState.animateScrollToItem(0)
    }
}

@Composable
fun rememberMessageListState(): MessageListState {
    val lazyListState = rememberLazyListState()
    return remember { MessageListState(lazyListState) }
}

@Composable
fun MessageList(
    messages: List<ChatMessage>,
    state: MessageListState = rememberMessageListState(),
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = state.lazyListState,
        reverseLayout = true,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        itemsIndexed(messages) { _, msg ->
            Message(
                type = msg.type,
                text = msg.text,
                messageStatusState = msg.status
            )
        }
    }
}
