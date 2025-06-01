package com.alisher.aside.ui.debug

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alisher.aside.ui.components.ButtonState
import com.alisher.aside.ui.components.ButtonType
import com.alisher.aside.ui.components.SendQueueButton
import com.alisher.aside.ui.theme.AsideTheme

@Preview(showBackground = true, backgroundColor = 0xFF202020)
@Composable
fun SendQueueButtonPreview() {
    AsideTheme {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                SendQueueButton(ButtonType.Send, ButtonState.Default)
                SendQueueButton(ButtonType.Send, ButtonState.Pressed)
                SendQueueButton(ButtonType.Send, ButtonState.Disabled)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                SendQueueButton(ButtonType.Queue, ButtonState.Default)
                SendQueueButton(ButtonType.Queue, ButtonState.Pressed)
                SendQueueButton(ButtonType.Queue, ButtonState.Disabled)
            }
        }
    }
}
