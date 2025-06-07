package com.alisher.aside.ui.components

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import com.alisher.aside.ui.theme.AsideTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object TopToastManager {
    private val scope = CoroutineScope(Dispatchers.Main)
    private const val SHORT_DURATION = 2000L
    private const val LONG_DURATION = 3500L

    var message by mutableStateOf<String?>(null)
        private set

    fun show(message: String, duration: Int = Toast.LENGTH_SHORT) {
        val delayMillis = if (duration == Toast.LENGTH_LONG) LONG_DURATION else SHORT_DURATION
        scope.launch {
            this@TopToastManager.message = message
            delay(delayMillis)
            this@TopToastManager.message = null
        }
    }
}

@Composable
fun TopToastHost(modifier: Modifier = Modifier) {
    val currentMessage = TopToastManager.message
    Box(modifier = modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = currentMessage != null,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(AsideTheme.colors.grayGraphene, RoundedCornerShape(4.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = currentMessage ?: "",
                    color = AsideTheme.colors.whitePure,
                    style = AsideTheme.typography.bodyLarge
                )
            }
        }
    }
}
