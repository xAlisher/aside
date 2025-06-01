package com.alisher.aside.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alisher.aside.ui.theme.AsideTheme

/** Direction of a message bubble. */
enum class MessageType { In, Out }

/**
 * Minimal chat message bubble.
 * @param type Incoming (`In`) or outgoing (`Out`). Controls alignment and status indicator.
 * @param text Optional message text.
 * @param messageStatusState Optional status for outgoing messages.
 */
@Composable
fun Message(
    type: MessageType,
    text: String = "",
    messageStatusState: MessageStatus? = null,
    modifier: Modifier = Modifier
) {
    val horizontalAlignment = if (type == MessageType.In) Alignment.Start else Alignment.End

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = horizontalAlignment
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = horizontalAlignment
        ) {
            if (text.isNotEmpty()) {
                Text(
                    text = text,
                    style = AsideTheme.typography.bodyMedium,
                    color = AsideTheme.colors.whitePure,
                    textAlign = if (type == MessageType.In) TextAlign.Start else TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        if (type == MessageType.Out && messageStatusState != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                MessageStatusIndicator(status = messageStatusState)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun MessagePreview() {
    AsideTheme {
        Column(Modifier.fillMaxWidth()) {
            Message(type = MessageType.In, text = "Hello there")
            Spacer(Modifier.height(12.dp))
            Message(type = MessageType.Out, text = "General Kenobi", messageStatusState = MessageStatus.Sent)
        }
    }
}
