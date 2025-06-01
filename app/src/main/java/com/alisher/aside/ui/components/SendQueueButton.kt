package com.alisher.aside.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.alisher.aside.ui.theme.AsideTheme
import com.alisher.aside.R
import com.alisher.aside.ui.theme.GrayCharcoal

enum class ButtonType { Send, Queue }

enum class ButtonState { Default, Pressed, Disabled }

@Composable
fun SendQueueButton(
    type: ButtonType,
    state: ButtonState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val backgroundColor = when (state) {
        ButtonState.Default -> AsideTheme.colors.grayGraphene
        ButtonState.Pressed -> GrayCharcoal
        ButtonState.Disabled -> AsideTheme.colors.grayGraphene
    }

    val iconRes = when (type) {
        ButtonType.Send -> R.drawable.ic_send
        ButtonType.Queue -> R.drawable.ic_queue
    }

    val tint: Color = if (state == ButtonState.Default) Color.White else AsideTheme.colors.grayDust

    Box(
        modifier = modifier
            .size(48.dp)
            .background(backgroundColor, shape = RoundedCornerShape(2.dp))
            .padding(8.dp)
            .clickable(enabled = state != ButtonState.Disabled) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(32.dp)
        )
    }
}
