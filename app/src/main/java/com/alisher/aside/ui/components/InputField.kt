package com.alisher.aside.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.alisher.aside.ui.theme.AsideTheme

@Composable
fun InputField(
    text: String,
    onValueChange: (String) -> Unit,
    buttonType: ButtonType = ButtonType.Send,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

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
                .focusRequester(focusRequester)
                .align(Alignment.Top),
            textStyle = AsideTheme.typography.bodyMedium.copy(
                color = AsideTheme.colors.whitePure
            ),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Default),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            singleLine = false
        )

        Spacer(Modifier.width(16.dp))

        val buttonState = if (internalValue.text.isEmpty()) ButtonState.Disabled else ButtonState.Default
        SendQueueButton(
            type = buttonType,
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
