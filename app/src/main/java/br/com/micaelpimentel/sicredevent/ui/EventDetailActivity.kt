package br.com.micaelpimentel.sicredevent.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ShareActionProvider
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

    private lateinit var event: Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupObservers()
        tryGetEvent()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.event_details_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_item_share -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_SUBJECT, event.title)
                    putExtra(Intent.EXTRA_TEXT, event.description)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }

        }
        return super.onOptionsItemSelected(item)
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
            this.event = it
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
