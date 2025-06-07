package com.alisher.aside.util

import android.content.Context
import android.view.Gravity
import android.widget.Toast

fun showTopToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
    val toast = Toast.makeText(context, message, duration)
    toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
    toast.show()
}
