package com.alisher.aside.ui.debug

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alisher.aside.ui.components.Message
import com.alisher.aside.ui.components.MessageStatus
import com.alisher.aside.ui.components.MessageType
import com.alisher.aside.ui.theme.AsideTheme

/**
 * Renders a short conversation to visually verify the Message component.
 * Outgoing messages show delivery status, incoming messages do not.
 */
@Composable
fun MessagePreviewScreen() {
    AsideTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AsideTheme.colors.blackHole)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Message(
                type = MessageType.Out,
                text = "They know it was you",
                messageStatusState = MessageStatus.Delivered
            )
            Message(
                type = MessageType.In,
                text = "Erasing drives"
            )
            Message(
                type = MessageType.Out,
                text = "You don\u2019t have time for that, they\u2019ll be at your place in 5 min",
                messageStatusState = MessageStatus.Delivered
            )
            Message(
                type = MessageType.In,
                text = "I\u2019ll be done in 3"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MessagePreview() {
    MessagePreviewScreen()
}
