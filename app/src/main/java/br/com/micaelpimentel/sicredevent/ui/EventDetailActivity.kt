package br.com.micaelpimentel.sicredevent.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.micaelpimentel.sicredevent.R
import br.com.micaelpimentel.sicredevent.api.model.Checkin
import br.com.micaelpimentel.sicredevent.api.model.Event
import br.com.micaelpimentel.sicredevent.databinding.ActivityEventDetailBinding
import br.com.micaelpimentel.sicredevent.extensions.formatCurrencyToBr
import br.com.micaelpimentel.sicredevent.extensions.tryLoadImage
import br.com.micaelpimentel.sicredevent.ui.dialog.CheckinFormDialog
import br.com.micaelpimentel.sicredevent.viewmodel.EventsViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

const val EVENT_ID_TAG = "event_id_tag"

class EventDetailActivity : AppCompatActivity() {
    private val eventsViewModel: EventsViewModel by viewModel()
    private val binding by lazy {
        ActivityEventDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupObservers()
        tryGetEvent()
    }

    private fun setupObservers() {
        setupEventDetailsObservers()
        setupCheckinObservers()
    }

    private fun setupCheckinObservers() {
        eventsViewModel.checkinResponse.observe(this) {
            showSnackbar(R.string.successful_checkin_message)
        }
        eventsViewModel.checkinError.observe(this) {
            if (it) {
                showSnackbar(R.string.unsuccessful_checkin_message)
            }
        }
    }

    private fun setupEventDetailsObservers() {
        eventsViewModel.eventDetailsResponse.observe(this) {
            setupViews(it)
        }

        eventsViewModel.getEventDetailsError.observe(this) {
            showSnackbar(R.string.unsuccessful_load_event_details)
            Log.d("MICAEL", "Erro ao carregar dados do evento\n ${it.code}")
        }
    }

    private fun showSnackbar(stringResId: Int) {
        Snackbar.make(
            binding.root,
            getString(stringResId),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun tryGetEvent() {
        intent.getStringExtra(EVENT_ID_TAG)?.let {
            eventsViewModel.getEventDetails(it)
        } ?: finish()
    }

    private fun setupViews(event: Event) {
        with(binding) {
            eventNameTextView.text = event.title
            eventDescriptionTextView.text = event.description
            eventImageView.tryLoadImage(event.image)
            eventPriceTextView.text = event.price.formatCurrencyToBr()
        }
        setupFab(event)
    }

    private fun setupFab(event: Event) {
        binding.floatingActionButton.setOnClickListener {
            CheckinFormDialog(this)
                .show { name, email ->
                    val checkinRequest = Checkin(
                        eventId = event.id,
                        name = name,
                        email = email
                    )
                    eventsViewModel.requestEventCheckin(checkinRequest)
                }
        }
    }
}
