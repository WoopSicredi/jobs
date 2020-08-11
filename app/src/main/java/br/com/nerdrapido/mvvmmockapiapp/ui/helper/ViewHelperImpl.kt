package br.com.nerdrapido.mvvmmockapiapp.ui.helper

import android.widget.ImageView
import br.com.nerdrapido.mvvmmockapiapp.R
import com.bumptech.glide.Glide

/**
 * Created By FELIPE GUSBERTI @ 11/08/2020
 */
class ViewHelperImpl : ViewHelper {

    override fun loadImageUrlIntoView(imageUrl: String, imageView: ImageView) {
        Glide.with(imageView.context.applicationContext)
            .load(imageUrl)
            .placeholder(R.drawable.img_progress)
            .error(R.drawable.ic_broken_image)
            .into(imageView)
    }
}