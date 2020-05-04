package com.example.appteste.extensions

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.toolbar_custom.*

fun AppCompatActivity.setToolbarTitle(title: String) {
    toolbar_title.text = title
}