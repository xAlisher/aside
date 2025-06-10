package com.alisher.aside.ui.debug

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alisher.aside.ui.components.InputField
import com.alisher.aside.ui.theme.AsideTheme

@Preview(showBackground = true, backgroundColor = 0xFF202020)
@Composable
fun InputFieldPreview() {
    AsideTheme {
        val text1 = remember { mutableStateOf("Hello") }
        val text2 = remember { mutableStateOf("") }

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            InputField(
                text = text1.value,
                onValueChange = { text1.value = it },
                onSend = {},
            )

            InputField(
                text = text2.value,
                onValueChange = { text2.value = it },
                onSend = {},
            )
        }
    }
}
