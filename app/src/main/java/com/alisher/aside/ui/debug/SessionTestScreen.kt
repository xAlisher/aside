package com.alisher.aside.ui.debug

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alisher.aside.ui.session.SessionViewModel

@Composable
fun SessionTestScreen(viewModel: SessionViewModel = viewModel()) {
    val connectionState by viewModel.connectionState.collectAsState()
    val sessionState by viewModel.sessionState.collectAsState()
    val messageStatus by viewModel.messageStatus.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Connection: $connectionState")
        Text(text = "Session: $sessionState")
        Text(text = "Message: $messageStatus")

        Spacer(Modifier.height(16.dp))

        Button(onClick = { viewModel.startSession() }) {
            Text("Start Session")
        }
        Button(onClick = { viewModel.simulatePeerJoin() }) {
            Text("Simulate Peer Join")
        }
        Button(onClick = { viewModel.simulatePeerLeave() }) {
            Text("Simulate Peer Leave")
        }
        Button(onClick = { viewModel.queueMessage() }) {
            Text("Queue Message")
        }
        Button(onClick = { viewModel.markMessageSent() }) {
            Text("Mark Message Sent")
        }
        Button(onClick = { viewModel.exitSession() }) {
            Text("Exit Session")
        }
    }
}
