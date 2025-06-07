package com.alisher.aside

import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.alisher.aside.ui.theme.AsideTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.systemBars

@Composable
fun AsideScreen(
    onCreate: () -> Unit,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AsideTheme.colors.blackHole)
            .padding(24.dp)
            .windowInsetsPadding(WindowInsets.systemBars), // ⬅ optional to avoid overlap
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Let’s step aside",
            color = AsideTheme.colors.whitePure,
            style = AsideTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(AsideTheme.colors.grayGraphene)
                .clickable {
                    val clipboard = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                    val clip = clipboard.primaryClip
                    val pasted = if (clip != null && clip.itemCount > 0) {
                        clip.getItemAt(0).text?.toString()
                    } else {
                        "Clipboard is empty"
                    }
                    Toast.makeText(context, "Pasted: $pasted", Toast.LENGTH_SHORT).show()
                },
            contentAlignment = Alignment.Center // ✅ center it properly
        ) {
            Text(
                text = "⎘ Paste",
                color = AsideTheme.colors.whitePure,
                style = AsideTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(AsideTheme.colors.grayGraphene)
                .clickable { onCreate() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "+ Create",
                color = AsideTheme.colors.whitePure,
                style = AsideTheme.typography.bodyLarge
            )
        }

    }
}
