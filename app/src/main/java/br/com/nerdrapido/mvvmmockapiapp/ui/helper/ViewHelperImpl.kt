package br.com.nerdrapido.mvvmmockapiapp.ui.helper

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import br.com.nerdrapido.mvvmmockapiapp.R
import br.com.nerdrapido.mvvmmockapiapp.ui.viewComponent.ItemInfoView
import br.com.nerdrapido.mvvmmockapiapp.ui.viewComponent.ItemInfoViewMultiline
import br.com.nerdrapido.mvvmmockapiapp.ui.viewComponent.ItemInfoViewSingleLine
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_event_detail.*

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

    override fun addInfoView(
        context: Context,
        title: String,
        info: String,
        container: ViewGroup,
        multiline: Boolean
    ) {
        val infoView: ItemInfoView =
            if (multiline) ItemInfoViewMultiline(context) else ItemInfoViewSingleLine(context)
        infoView.setTitle(title)
        infoView.setInfo(info)
        container.addView(infoView as View)
    }
}