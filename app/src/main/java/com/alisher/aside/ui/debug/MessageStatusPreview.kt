package com.alisher.aside.ui.debug

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alisher.aside.ui.components.MessageStatus
import com.alisher.aside.ui.components.MessageStatusIndicator
import com.alisher.aside.ui.theme.AsideTheme

@Preview(showBackground = false)
@Composable
fun MessageStatusPreview() {
    AsideTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(AsideTheme.colors.blackHole)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            MessageStatusIndicator(status = MessageStatus.Queued)
            Spacer(Modifier.height(8.dp))
            MessageStatusIndicator(status = MessageStatus.Sent)
            Spacer(Modifier.height(8.dp))
            MessageStatusIndicator(status = MessageStatus.Delivered)
            Spacer(Modifier.height(8.dp))
            MessageStatusIndicator(status = MessageStatus.Failed)
        }
    }
}
