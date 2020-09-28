package br.com.angelorobson.templatemvi.view.utils

import android.content.Context
import android.widget.Toast

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(
            this,
            message,
            duration
    ).show()
}