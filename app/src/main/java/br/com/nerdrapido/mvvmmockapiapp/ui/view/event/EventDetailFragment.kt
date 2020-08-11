package br.com.nerdrapido.mvvmmockapiapp.ui.view.event

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
import com.bumptech.glide.Glide
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
    }

    private fun hydrateEvent(event: Event) {
        viewHelper.loadImageUrlIntoView(event.image, eventCoverIv)
    }
}