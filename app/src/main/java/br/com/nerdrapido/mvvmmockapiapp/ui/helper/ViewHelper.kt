package br.com.nerdrapido.mvvmmockapiapp.ui.helper

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import br.com.nerdrapido.mvvmmockapiapp.ui.viewComponent.ItemInfoView
import br.com.nerdrapido.mvvmmockapiapp.ui.viewComponent.ItemInfoViewMultiline
import br.com.nerdrapido.mvvmmockapiapp.ui.viewComponent.ItemInfoViewSingleLine

/**
 * Created By FELIPE GUSBERTI @ 11/08/2020
 */
interface ViewHelper {

    fun loadImageUrlIntoView(imageUrl : String, imageView: ImageView)

    /**
     * Adiciona uma [ItemInfoView] com a informação passad nos parâmetros.
     *
     * @param context é o Context da activity
     * @param title é o título da informação.
     * @param info é a informação em si.
     * @param container é a view em que a info vai ser add.
     * @param multiline se true é criado uam [ItemInfoViewMultiline] se falso
     * [ItemInfoViewSingleLine]
     */
    fun addInfoView(
        context: Context,
        title: String,
        info: String,
        container: ViewGroup,
        multiline: Boolean = false
    )
}