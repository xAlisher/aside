package com.alisher.aside.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.SolidColor
import com.alisher.aside.ui.theme.AsideTheme

/* inset helpers */
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding

@Composable
fun InputField(
    text: String,
    onValueChange: (String) -> Unit,
    buttonType: ButtonType = ButtonType.Send,
    modifier: Modifier = Modifier
) {
    val keyboard   = LocalSoftwareKeyboardController.current
    val requester  = remember { FocusRequester() }
    val focusMgr   = LocalFocusManager.current

    LaunchedEffect(Unit) {
        requester.requestFocus()
        keyboard?.show()
    }

    Column(
        modifier = modifier
            .background(AsideTheme.colors.blackHole)
            // nav-bar inset first (old 3-button mode)
            .navigationBarsPadding()
            // IME inset second (keyboard + suggestion strip)
            .imePadding()
    ) {
        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = AsideTheme.colors.grayGraphene
        )

        Row(
            modifier = Modifier
                // tiny safety pad for OEM quirks
                .padding(bottom = 8.dp)
                .padding(horizontal = 16.dp)
                .heightIn(min = 48.dp),
            verticalAlignment = Alignment.Bottom
        ) {
        var internal by rememberSaveable(stateSaver = TextFieldValue.Saver) {
            mutableStateOf(TextFieldValue(text))
        }

        LaunchedEffect(text) {
            if (text != internal.text) internal = TextFieldValue(text)
        }

        BasicTextField(
            value = internal,
            onValueChange = {
                internal = it
                onValueChange(it.text)
            },
            modifier = Modifier
                .weight(1f)
                .focusRequester(requester)
                .align(Alignment.Top),
            textStyle = AsideTheme.typography.bodyMedium.copy(
                color = AsideTheme.colors.whitePure
            ),
            keyboardOptions  = KeyboardOptions.Default.copy(imeAction = ImeAction.Default),
            keyboardActions  = KeyboardActions(onDone = { focusMgr.clearFocus() }),
            singleLine       = false,
            cursorBrush      = SolidColor(AsideTheme.colors.whitePure)
        )

        Spacer(Modifier.width(16.dp))

        val btnState =
            if (internal.text.isEmpty()) ButtonState.Disabled else ButtonState.Default

        SendQueueButton(
            type  = buttonType,
            state = btnState,
            onClick = {
                keyboard?.hide()
                onValueChange("")
                internal = TextFieldValue("")
            },
            modifier = Modifier.align(Alignment.Bottom)
        )
    }
}

}
