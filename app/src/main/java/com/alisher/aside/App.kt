package com.alisher.aside

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alisher.aside.ui.components.*
import com.alisher.aside.ui.session.SessionViewModel

private const val DUMMY_INVITE =
    "Let\u2019s step aside: 03b1a3cf0ae3f8b6cc1937124e36f51b9e8e3f024f18ec1479d07ec0f27c50a3d9"

enum class AppScreen { Home, Chat }

@Composable
fun AsideApp() {
    val context      = LocalContext.current
    var screen       by remember { mutableStateOf(AppScreen.Home) }
    var draft        by rememberSaveable { mutableStateOf("") }
    val viewModel: SessionViewModel = viewModel()
    val peerState    by viewModel.peerState.collectAsState()
    val messages     by viewModel.messages.collectAsState()

    when (screen) {
        AppScreen.Home -> AsideScreen(
            onCreate = {
                (context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager)
                    .setPrimaryClip(ClipData.newPlainText("invite", DUMMY_INVITE))
                screen = AppScreen.Chat
            }
        )

        AppScreen.Chat -> ChatScreen(
            peerState     = peerState,
            draft         = draft,
            onDraftChange = { draft = it },
            onSend        = { viewModel.submitMessage(draft) },
            onCycle       = { viewModel.debugCycle() },
            onExit        = {
                viewModel.exit()
                screen = AppScreen.Home
            },
            messages      = messages
        )
    }
}
