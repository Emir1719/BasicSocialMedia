package com.emirozturk.socialmedia.ToastMessage
import android.content.Context
import android.widget.Toast

fun showMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}