package br.com.micaelpimentel.sicredevent.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.micaelpimentel.sicredevent.R
import br.com.micaelpimentel.sicredevent.databinding.ActivityMainBinding
import br.com.micaelpimentel.sicredevent.ui.adapter.EventListAdapter
import br.com.micaelpimentel.sicredevent.viewmodel.EventsViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val eventsViewModel: EventsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupObservers()
        tryGetEvents()
    }

    private fun tryGetEvents() {
        eventsViewModel.getEvents()
    }

    private fun setupObservers() {
        eventsViewModel.eventsResponse.observe(this) { eventList ->
            binding.recyclerView.adapter = EventListAdapter(eventList).apply {
                onItemClickListener = { eventId ->
                    val intent = Intent(
                        this@MainActivity,
                        EventDetailActivity::class.java
                    ).apply {
                        putExtra(EVENT_ID_TAG, eventId)
                    }
                    startActivity(intent)
                }
            }
        }
        eventsViewModel.getEventsError.observe(this) {
            Snackbar.make(
                binding.root,
                getString(R.string.erro_load_events),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}