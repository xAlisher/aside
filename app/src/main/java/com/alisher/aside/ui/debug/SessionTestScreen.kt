/*
package com.alisher.aside.ui.debug

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import com.alisher.aside.util.showTopToast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alisher.aside.ui.session.SessionViewModel

@Composable
fun SessionTestScreen(viewModel: SessionViewModel = viewModel()) {
    val context = LocalContext.current

    val connectionState by viewModel.connectionState.collectAsState()
    val sessionState by viewModel.sessionState.collectAsState()
    val messageStatus by viewModel.messageStatus.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text("Connection: $connectionState", fontFamily = FontFamily.Monospace)
            Text("Session: $sessionState", fontFamily = FontFamily.Monospace)
            Text("Message: $messageStatus", fontFamily = FontFamily.Monospace)
        }

        Divider()

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Session Controls")
            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                viewModel.startSession()
                val fakeInvite = "Let’s step aside: ${System.currentTimeMillis().toString(16)}"
                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                clipboard.setPrimaryClip(ClipData.newPlainText("invite", fakeInvite))
            }) {
                Text("Start Session")
            }
            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                viewModel.exitSession()
                showTopToast(context, "Nothing here")
            }) {
                Text("Exit Session")
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Peer Simulation")
            Button(modifier = Modifier.fillMaxWidth(), onClick = { viewModel.simulatePeerJoin() }) {
                Text("Simulate Peer Join")
            }
            Button(modifier = Modifier.fillMaxWidth(), onClick = { viewModel.simulatePeerLeave() }) {
                Text("Simulate Peer Leave")
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Message Flow")
            Button(modifier = Modifier.fillMaxWidth(), onClick = { viewModel.queueMessage() }) {
                Text("Queue Message")
            }
            Button(modifier = Modifier.fillMaxWidth(), onClick = { viewModel.markMessageSent() }) {
                Text("Mark Message Sent")
            }
        }
    }
}
*/