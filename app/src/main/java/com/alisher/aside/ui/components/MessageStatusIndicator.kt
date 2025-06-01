package com.alisher.aside.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alisher.aside.ui.theme.AsideTheme

/**
 * Four possible states:
 *  • Queued    → "⧗ Queued to send"    (grayDust)
 *  • Sent      → "✓ Sent"              (whitePure)
 *  • Delivered → "✓✓ Delivered"        (purplePrivate)
 *  • Failed    → "⟳ Failed to send"     (redAlarm)
 *
 * Both icon + text use Roboto 12sp from AsideTheme.typography.labelSmall.
 */
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
    // Pick the combined “icon + label” string and a color from the theme:
    val (combinedText, color) = when (status) {
        MessageStatus.Queued    -> "⧗ Queued to send" to AsideTheme.colors.grayDust
        MessageStatus.Sent      -> "✓ Sent"           to AsideTheme.colors.whitePure
        MessageStatus.Delivered -> "✓✓ Delivered"     to AsideTheme.colors.purplePrivate
        MessageStatus.Failed    -> "⟳ Failed to send"  to AsideTheme.colors.redAlarm
    }

    Text(
        text     = combinedText,
        style    = AsideTheme.typography.labelSmall,
        color    = color,
        modifier = modifier
    )
}
