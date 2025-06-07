package com.alisher.aside.util

import android.content.Context
import android.widget.Toast
import com.alisher.aside.ui.components.TopToastManager

fun showTopToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
    TopToastManager.show(message, duration)
}
