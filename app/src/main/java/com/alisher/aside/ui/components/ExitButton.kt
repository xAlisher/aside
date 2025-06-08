package com.alisher.aside.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import com.alisher.aside.ui.theme.AsideTheme
import com.alisher.aside.util.showTopToast

@Composable
fun ExitButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Box(
        contentAlignment = Alignment.CenterEnd,
        modifier = modifier
            .sizeIn(minWidth = 48.dp, minHeight = 48.dp)
            .clickable {
                showTopToast(context, "Nothing here")
                onClick()
            }   // ≥48 dp touch area
    ) {
        Text(
            text = "Exit",
            style = AsideTheme.typography.bodyLarge,
            color = AsideTheme.colors.whitePure
        )
    }
}
