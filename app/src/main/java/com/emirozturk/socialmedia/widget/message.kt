package com.emirozturk.socialmedia.widget
import android.app.AlertDialog
import android.content.Context
import android.widget.Toast

fun showMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun createProgressDialog(context: Context): AlertDialog {
    val builder = AlertDialog.Builder(context)
    builder.setCancelable(false) // Kullanıcı geri tuşuyla kapatamasın
    return builder.create()
}