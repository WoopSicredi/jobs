package br.com.nerdrapido.mvvmmockapiapp.ui.viewComponent

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.nerdrapido.mvvmmockapiapp.R
import kotlinx.android.synthetic.main.view_item_info.view.*

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
abstract class ItemInfoView constructor(
    context: Context
) : ConstraintLayout(context, null, 0) {

    private val infoDefaultValue = context.getString(R.string.not_informed)

    protected abstract val layoutId: Int

    private var title: String = infoDefaultValue

    private var info: String = infoDefaultValue

    fun setTitle(newTitle : String) {
        title = newTitle
        updateText(title, info)
    }

    fun setInfo(newInfo : String) {
        info = newInfo
        updateText(title, info)
    }

    private fun updateText(title: String, info: String) {
        titleTv.text = context.getString(R.string.view_info_title, title)
        infoTv.text = info
    }
}