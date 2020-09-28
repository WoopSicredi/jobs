package br.com.angelorobson.templatemvi.view.events

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.angelorobson.templatemvi.R
import br.com.angelorobson.templatemvi.model.domains.Event
import br.com.angelorobson.templatemvi.view.events.widgets.EventAdapter
import br.com.angelorobson.templatemvi.view.getViewModel
import br.com.angelorobson.templatemvi.view.utils.setVisibleOrGone
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_event.*


class EventFragment : Fragment(R.layout.fragment_event) {

    private val mCompositeDisposable = CompositeDisposable()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val eventClickPublisherSubject = PublishSubject.create<Event>()

        val adapter = EventAdapter { eventClickPublisherSubject.onNext(it) }

        setupRecyclerView(adapter)

        val disposable = Observable.mergeArray(eventClickPublisherSubject.map { GoToEventDetailIntent(it) })
                .compose(getViewModel(EventViewModel::class).init(InitialIntent))
                .subscribe(
                        {
                            when (it.eventResult) {
                                is EventResult.EventsLoaded -> {
                                    adapter.submitList(it.eventResult.events)
                                    event_progress_horizontal.setVisibleOrGone(false)
                                }
                                is EventResult.Error -> {
                                }
                            }
                        },
                        {
                            event_progress_horizontal.setVisibleOrGone(false)
                        }
                )

        mCompositeDisposable.add(disposable)
    }

    private fun setupRecyclerView(eventAdapter: EventAdapter) {
        event_recycler_view?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = eventAdapter
        }
    }

    override fun onDestroy() {
        mCompositeDisposable.clear()
        super.onDestroy()
    }
}
