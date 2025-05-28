package com.alisher.aside

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alisher.aside.ui.theme.*
import com.alisher.aside.AsideScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AsideTheme {
                AsideScreen()
            }
        }
    }
}
