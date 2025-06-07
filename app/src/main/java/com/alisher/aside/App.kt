package com.alisher.aside

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import com.alisher.aside.ui.components.*
import com.alisher.aside.ui.components.TopToastHost
import com.alisher.aside.util.showTopToast

private const val DUMMY_INVITE =
    "Let\u2019s step aside: 03b1a3cf0ae3f8b6cc1937124e36f51b9e8e3f024f18ec1479d07ec0f27c50a3d9"

enum class AppScreen { Home, Chat }

@Composable
fun AsideApp() {
    val context      = LocalContext.current
    var screen       by remember { mutableStateOf(AppScreen.Home) }
    var draft        by rememberSaveable { mutableStateOf("") }
    val peerState    = remember { PeerState.Offline }   // stub until real connect

    Box(Modifier.fillMaxSize()) {
        when (screen) {
            AppScreen.Home -> AsideScreen(
                onCreate = {
                    (context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager)
                        .setPrimaryClip(ClipData.newPlainText("invite", DUMMY_INVITE))
                    showTopToast(
                        context,
                        "Invite copied to clipboard. Send it to your peer."
                    )
                    screen = AppScreen.Chat
                }
            )

            AppScreen.Chat -> ChatScreen(
                peerState     = peerState,
                draft         = draft,
                onDraftChange = { draft = it },
                onExit        = { screen = AppScreen.Home }
            )
        }
        TopToastHost(Modifier.fillMaxSize())
    }
}
