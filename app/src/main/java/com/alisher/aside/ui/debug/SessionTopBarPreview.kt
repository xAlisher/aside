package com.alisher.aside.ui.debug

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.alisher.aside.ui.components.SessionTopBar
import com.alisher.aside.ui.components.PeerState
import com.alisher.aside.ui.theme.AsideTheme

@Preview(showBackground = true, backgroundColor = 0xFF202020)
@Composable
fun SessionTopBarPreview() {
    AsideTheme {
        SessionTopBar(peerState = PeerState.Connected, onExit = {}, onDebugCycle = {})
    }
}
