package com.alisher.aside.ui.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alisher.aside.ui.components.ChatMessage
import com.alisher.aside.ui.components.MessageStatus
import com.alisher.aside.ui.components.MessageType
import com.alisher.aside.ui.components.PeerState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class SessionViewModel : ViewModel() {

    private val _peerState = MutableStateFlow(PeerState.Offline)
    val peerState: StateFlow<PeerState> = _peerState

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    private var handshakeJob: Job? = null
    private var graceJob: Job? = null
    private var idleJob: Job? = null
    private var sendJob: Job? = null

    private fun toConnecting() {
        _peerState.value = PeerState.Connecting
        handshakeJob?.cancel()
        handshakeJob = viewModelScope.launch {
            delay(2_000)
            if (_peerState.value == PeerState.Connecting) {
                toConnected()
            }
        }
        startGraceTimer()
    }

    private fun toConnected() {
        _peerState.value = PeerState.Connected
        graceJob?.cancel()
        startIdleTimer()
        startSendJob()
    }

    private fun startIdleTimer() {
        idleJob?.cancel()
        idleJob = viewModelScope.launch {
            delay(15_000)
            if (_peerState.value == PeerState.Connected) {
                toConnecting()
            }
        }
    }

    private fun startGraceTimer() {
        graceJob?.cancel()
        graceJob = viewModelScope.launch {
            delay(30_000)
            if (_peerState.value == PeerState.Connecting) {
                _peerState.value = PeerState.Offline
            }
        }
    }

    fun exit() {
        _peerState.value = PeerState.Exited
        handshakeJob?.cancel()
        graceJob?.cancel()
        idleJob?.cancel()
        sendJob?.cancel()
        _messages.value = emptyList()
    }

    fun submitMessage(text: String) {
        if (_peerState.value == PeerState.Offline) {
            toConnecting()
        }

        val status = if (_peerState.value == PeerState.Connected) {
            MessageStatus.Sent
        } else {
            MessageStatus.Queued
        }

        val msg = ChatMessage(type = MessageType.Out, text = text, status = status)
        _messages.update { listOf(msg) + it }

        if (_peerState.value == PeerState.Connected) {
            startSendJob()
        }
    }

    private fun startSendJob() {
        if (sendJob?.isActive == true) return
        sendJob = viewModelScope.launch {
            while (_peerState.value == PeerState.Connected) {
                val index = _messages.value.lastIndexOfFirst { it.status == MessageStatus.Queued }
                if (index == -1) break
                deliver(index)
            }
        }
    }

    private suspend fun deliver(index: Int) {
        updateMessage(index, MessageStatus.Sent)
        delay(500)
        if (Random.nextBoolean()) {
            updateMessage(index, MessageStatus.Failed)
        } else {
            delay(700)
            updateMessage(index, MessageStatus.Delivered)
        }
    }

    private fun updateMessage(index: Int, status: MessageStatus) {
        _messages.update { list ->
            list.toMutableList().also { it[index] = it[index].copy(status = status) }
        }
    }

    private fun <T> List<T>.lastIndexOfFirst(predicate: (T) -> Boolean): Int {
        for (i in indices.reversed()) {
            if (predicate(this[i])) return i
        }
        return -1
    }

    fun debugCycle() {
        when (_peerState.value) {
            PeerState.Offline    -> toConnecting()
            PeerState.Connecting -> toConnected()
            PeerState.Connected  -> _peerState.value = PeerState.Exited
            PeerState.Exited     -> _peerState.value = PeerState.Offline
        }
    }
}

