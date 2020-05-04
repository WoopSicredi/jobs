package com.example.appteste.extensions

import android.content.Context
import com.example.appteste.R

fun String.monetize(context: Context): String {
    return context.getString(R.string.price_label).plus(this)
}