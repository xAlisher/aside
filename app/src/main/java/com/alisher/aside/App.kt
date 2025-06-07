package com.alisher.aside

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.alisher.aside.ui.components.ChatScreen
import com.alisher.aside.ui.components.PeerState

private const val DUMMY_INVITE = "Let\u2019s step aside: 03b1a3cf0ae3f8b6cc1937124e36f51b9e8e3f024f18ec1479d07ec0f27c50a3d9"

enum class AppScreen { Home, Chat }

@Composable
fun AsideApp() {
    val context = LocalContext.current
    val current = remember { mutableStateOf(AppScreen.Home) }

    when (current.value) {
        AppScreen.Home -> AsideScreen(onCreate = {
            val clipboard = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(ClipData.newPlainText("invite", DUMMY_INVITE))
            Toast.makeText(context, "Invite copied to clipboard. Send it to your peer.", Toast.LENGTH_SHORT).show()
            current.value = AppScreen.Chat
        })
        AppScreen.Chat -> ChatScreen(peerState = PeerState.Offline, onExit = {
            current.value = AppScreen.Home
        })
    }
}
