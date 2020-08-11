package br.com.nerdrapido.mvvmmockapiapp.ui.view.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.nerdrapido.mvvmmockapiapp.R
import br.com.nerdrapido.mvvmmockapiapp.presentation.viewModel.event.EventViewModel
import br.com.nerdrapido.mvvmmockapiapp.ui.helper.ViewHelper
import kotlinx.android.synthetic.main.fragment_event_list.*
import org.koin.android.ext.android.inject

/**
 * Created By FELIPE GUSBERTI @ 10/08/2020
 */
class EventListFragment : Fragment(), View.OnClickListener {

    private val viewModel: EventViewModel by activityViewModels()

    private val viewHelper: ViewHelper by inject()

    private val adapter = EventListAdapter(emptyList(), viewHelper, this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_event_list, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventListRv.adapter = adapter
        eventListRv.layoutManager = LinearLayoutManager(context)
        viewModel.getEventList().observe(viewLifecycleOwner, Observer {
            adapter.setItems(it)
        })
    }

    override fun onClick(v: View?) {
        // v nunca é nula
        v!!.let {
            val viewHolder = it.tag as EventListAdapter.ViewHolder
            viewModel.onEventItemCLick(viewHolder.getEvent())
        }
    }
}
