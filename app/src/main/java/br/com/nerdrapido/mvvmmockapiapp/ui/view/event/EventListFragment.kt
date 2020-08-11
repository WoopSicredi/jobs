package br.com.nerdrapido.mvvmmockapiapp.ui.view.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.nerdrapido.mvvmmockapiapp.R
import br.com.nerdrapido.mvvmmockapiapp.presentation.viewModel.event.EventViewModel
import kotlinx.android.synthetic.main.fragment_event_list.*

/**
 * Created By FELIPE GUSBERTI @ 10/08/2020
 */
class EventListFragment : Fragment(), View.OnClickListener {

    private val adapter = EventListAdapter(emptyList(), this)

    private val viewModel: EventViewModel by activityViewModels()

    companion object {
        fun newInstance() = EventListFragment()
    }

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
        Toast.makeText(context, "clique no item", Toast.LENGTH_SHORT).show()
    }


}