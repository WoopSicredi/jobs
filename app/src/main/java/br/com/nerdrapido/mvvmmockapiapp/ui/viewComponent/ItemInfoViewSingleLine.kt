package br.com.nerdrapido.mvvmmockapiapp.ui.viewComponent

import android.content.Context
import androidx.constraintlayout.widget.ConstraintSet
import br.com.nerdrapido.mvvmmockapiapp.R

/**
 * Created By FELIPE GUSBERTI @ 16/03/2020
 */
class ItemInfoViewSingleLine(context: Context) : ItemInfoView(context) {

    override val layoutId = R.layout.view_item_info

    init {
        inflate(context, layoutId, this)
        val set = ConstraintSet()
        set.clone(this)
    }
}