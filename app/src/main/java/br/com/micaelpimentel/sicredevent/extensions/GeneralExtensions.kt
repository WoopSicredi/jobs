package br.com.micaelpimentel.sicredevent.extensions

import android.view.View
import android.widget.ImageView
import br.com.micaelpimentel.sicredevent.R
import coil.load
import java.text.NumberFormat
import java.util.*

fun Float.formatCurrencyToBr(): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
    return formatter.format(this).replace("R$", "R$ ")
}

fun ImageView.tryLoadImage(url: String? = null) {
    load(url) {
        fallback(R.drawable.image_error)
        error(R.drawable.image_error)
        placeholder(R.drawable.placeholder)
    }
}

fun  ImageView.setupEventImage(url: String?) {
    url?.let {
        this.visibility = View.VISIBLE
        this.tryLoadImage(url)
    } ?: run {
        this.visibility = View.GONE
    }
}