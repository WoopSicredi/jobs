package br.com.angelorobson.templatemvi.view.utils

import android.content.Context
import android.view.View
import android.view.View.*
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import br.com.angelorobson.templatemvi.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.*


@BindingAdapter("visibleOrGone")
fun View.setVisibleOrGone(show: Boolean) {
    visibility = if (show) VISIBLE else GONE
}

@BindingAdapter("visible")
fun View.setVisible(show: Boolean) {
    visibility = if (show) VISIBLE else INVISIBLE
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    view.loadImage(imageUrl, getProgressDrawable(view.context))
}

@BindingAdapter("toMoneyFormat")
fun toMoneyFormat(textView: TextView, value: Float?) {
    textView.text = value?.toMoneyFormat()
}

@BindingAdapter("longToDate")
fun loadImage(textView: TextView, longNumber: Long?) {
    textView.text = longNumber?.toDateFormatted()
}

@BindingAdapter("inToString")
fun inToString(view: TextView, number: Int?) {
    view.text = number.toString()
}

fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
            .placeholder(progressDrawable)
            .error(R.drawable.image_not_found)

    Glide.with(context)
            .setDefaultRequestOptions(options)
            .load(uri)
            .apply(RequestOptions.circleCropTransform())
            .into(this)
}

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 25f
        start()
    }
}

@BindingAdapter("convertToFormatDateTime")
fun convertToFormatDateTime(textView: TextView, date: Date?) {
    textView.text = date?.formatDateTime()
}