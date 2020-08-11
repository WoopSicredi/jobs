package br.com.nerdrapido.mvvmmockapiapp.ui.view.event

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import br.com.nerdrapido.mvvmmockapiapp.R
import br.com.nerdrapido.mvvmmockapiapp.presentation.model.Event
import br.com.nerdrapido.mvvmmockapiapp.presentation.viewModel.event.EventViewModel
import br.com.nerdrapido.mvvmmockapiapp.ui.helper.ViewHelper
import br.com.nerdrapido.mvvmmockapiapp.ui.viewComponent.ItemInfoView
import br.com.nerdrapido.mvvmmockapiapp.ui.viewComponent.ItemInfoViewMultiline
import br.com.nerdrapido.mvvmmockapiapp.ui.viewComponent.ItemInfoViewSingleLine
import kotlinx.android.synthetic.main.fragment_event_detail.*
import org.koin.android.ext.android.inject

/**
 * Created By FELIPE GUSBERTI @ 11/08/2020
 */
class EventDetailFragment : Fragment() {

    private val viewModel: EventViewModel by activityViewModels()

    private val viewHelper: ViewHelper by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_event_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getEventSelected().observe(viewLifecycleOwner, Observer {
            hydrateEvent(it)
        })
        checkInBt.setOnClickListener {
            viewModel.onCheckIn()
        }
        checkInBt.text = getString(R.string.btn_fragment_event_detail_check_in_btn)
    }

    private fun hydrateEvent(event: Event) {
        collapsingToolbar.title = event.title
        overviewTv.text = getString(
            R.string.fragment_event_detail_overview,
            event.title,
            event.description
        )
        viewHelper.loadImageUrlIntoView(event.image, eventCoverIv)
        // aqui é feita a mágica para que o titulo da toolbar se adapte ao conteúdo.
        // A margem do título fica atrelada ao tamanho da overview do livro e é animada no scroll
        overviewTv.addOnLayoutChangeListener { _, _, top, _, _, _, _, _, _ ->
            collapsingToolbar.expandedTitleMarginBottom = checkInBt.bottom - top
        }
        detailInfoContainer.removeAllViews()
        addInfoView(
            getString(R.string.fragment_event_detail_title),
            event.title
        )
        addInfoView(
            getString(R.string.fragment_event_detail_valor),
            event.price.toString()
        )
        addInfoView(
            getString(R.string.fragment_event_detail_description),
            event.description,
            true
        )
    }

    /**
     * Adiciona uma [ItemInfoView] com a informação passad nos parâmetros.
     *
     * @param title é o título da informação.
     * @param info é a informação em si.
     * @param multiline se true é criado uam [ItemInfoViewMultiline] se falso
     * [ItemInfoViewSingleLine]
     */
    private fun addInfoView(title: String, info: String, multiline: Boolean = false) {
        context?.let {
            val infoView: ItemInfoView =
                if (multiline) ItemInfoViewMultiline(it) else ItemInfoViewSingleLine(it)
            infoView.title = title
            infoView.info = info
            detailInfoContainer.addView(infoView as View)
        }
    }
}