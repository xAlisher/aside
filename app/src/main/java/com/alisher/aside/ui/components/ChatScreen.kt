ackage com.alisher.aside.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alisher.aside.ui.components.SessionTopBar
import com.alisher.aside.ui.session.SessionViewModel

@Composable
fun ChatScreen(
    viewModel: SessionViewModel = viewModel(),
    onExit: () -> Unit
) {
    Column(Modifier.fillMaxSize()) {

        // top bar
        SessionTopBar(
            peerState = viewModel.peerState.collectAsState().value,
            onExit    = onExit
        )

        // TODO: message list + input go here
    }
}