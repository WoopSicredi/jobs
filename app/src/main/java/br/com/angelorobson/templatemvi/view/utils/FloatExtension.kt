package br.com.angelorobson.templatemvi.view.utils

import java.text.NumberFormat

fun Float.toMoneyFormat(): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    return format.format(this)
}