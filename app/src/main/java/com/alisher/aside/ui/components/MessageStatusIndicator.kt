package com.alisher.aside.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alisher.aside.ui.theme.AsideTheme

enum class MessageStatus {
    Queued,
    Sent,
    Delivered,
    Failed
}

@Composable
fun MessageStatusIndicator(
    status: MessageStatus,
    modifier: Modifier = Modifier
) {
    // Choose the Unicode icon character and color from the theme:
    val (icon, color) = when (status) {
        MessageStatus.Queued    -> "⧗"  to AsideTheme.colors.grayDust
        MessageStatus.Sent      -> "✓"   to AsideTheme.colors.whitePure
        MessageStatus.Delivered -> "✓✓"  to AsideTheme.colors.purplePrivate
        MessageStatus.Failed    -> "⟳"   to AsideTheme.colors.redAlarm
    }

    Row(modifier = modifier) {
        // Icon text (tag style, appropriate color)
        Text(
            text  = icon,
            style = AsideTheme.typography.labelSmall,
            color = color
        )
        Spacer(Modifier.width(8.dp))
        // Status label text
        Text(
            text  = when (status) {
                MessageStatus.Queued    -> "Queued to send"
                MessageStatus.Sent      -> "Sent"
                MessageStatus.Delivered -> "Delivered"
                MessageStatus.Failed    -> "Failed to send"
            },
            style = AsideTheme.typography.labelSmall,
            color = color
        )
    }
}
