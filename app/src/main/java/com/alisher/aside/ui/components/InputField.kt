package com.alisher.aside.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.alisher.aside.ui.theme.AsideTheme
import com.alisher.aside.ui.components.ButtonState
import com.alisher.aside.ui.components.ButtonType
import com.alisher.aside.ui.components.SendQueueButton

/**
 * Input field with auto focus and send button state.
 *
 * @param text current text value
 * @param onValueChange called when text changes
 * @param modifier optional modifier for the row container
 * @param placeholder optional placeholder shown when text is empty
 */
@Composable
fun InputField(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = ""
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    // Request focus and show keyboard when component appears
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Row(
        modifier = modifier
            .background(AsideTheme.colors.blackHole)
            .padding(horizontal = 16.dp)
            .heightIn(min = 48.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        var internalValue by rememberSaveable(stateSaver = TextFieldValue.Saver) {
            mutableStateOf(TextFieldValue(text))
        }

        // keep internal TextFieldValue in sync with parent text
        LaunchedEffect(text) {
            if (text != internalValue.text) {
                internalValue = TextFieldValue(text)
            }
        }

        BasicTextField(
            value = internalValue,
            onValueChange = {
                internalValue = it
                onValueChange(it.text)
            },
            modifier = Modifier
                .weight(1f)
                .focusRequester(focusRequester),
            textStyle = AsideTheme.typography.bodyMedium.copy(color = AsideTheme.colors.whitePure),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Default),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )

        if (internalValue.text.isEmpty() && placeholder.isNotEmpty()) {
            // Overlay placeholder when text is empty
            Box(modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 0.dp)) {
                Text(
                    text = placeholder,
                    style = AsideTheme.typography.bodyMedium,
                    color = AsideTheme.colors.grayDust
                )
            }
        }

        Spacer(Modifier.width(16.dp))

        val buttonState = if (internalValue.text.isEmpty()) ButtonState.Disabled else ButtonState.Default
        SendQueueButton(
            type = ButtonType.Send,
            state = buttonState,
            onClick = {
                keyboardController?.hide()
                onValueChange("")
                internalValue = TextFieldValue("")
            },
            modifier = Modifier.align(Alignment.Bottom)
        )
    }
}
