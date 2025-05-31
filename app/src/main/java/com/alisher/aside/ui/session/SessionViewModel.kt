package com.alisher.aside.ui.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SessionViewModel : ViewModel() {

    private val _connectionState = MutableStateFlow(ConnectionState.Offline)
    val connectionState: StateFlow<ConnectionState> = _connectionState

    private val _sessionState = MutableStateFlow(SessionState.Idle)
    val sessionState: StateFlow<SessionState> = _sessionState

    private val _messageStatus = MutableStateFlow(MessageStatus.Idle)
    val messageStatus: StateFlow<MessageStatus> = _messageStatus

    fun startSession() {
        viewModelScope.launch {
            _sessionState.value = SessionState.Waiting
            _connectionState.value = ConnectionState.Connecting
            delay(2000)
            _connectionState.value = ConnectionState.Online
        }
    }

    fun simulatePeerJoin() {
        viewModelScope.launch {
            delay(2000)
            _sessionState.value = SessionState.Paired
            _connectionState.value = ConnectionState.Online
        }
    }

    fun simulatePeerLeave() {
        _connectionState.value = ConnectionState.PeerLeft
        _sessionState.value = SessionState.Waiting
    }

    fun queueMessage() {
        _messageStatus.value = MessageStatus.Queued
    }

    fun markMessageSent() {
        _messageStatus.value = MessageStatus.Sent
    }

    fun exitSession() {
        _sessionState.value = SessionState.Exited
        _connectionState.value = ConnectionState.Offline
        _messageStatus.value = MessageStatus.Idle
    }
}

