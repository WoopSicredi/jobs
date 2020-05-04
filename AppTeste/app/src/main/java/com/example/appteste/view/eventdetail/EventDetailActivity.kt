package com.example.appteste.view.eventdetail

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.appteste.R
import com.example.appteste.extensions.getDate
import com.example.appteste.extensions.monetize
import com.example.appteste.model.CheckIn
import com.example.appteste.model.Event
import com.example.appteste.network.MainNetwork
import com.example.appteste.util.getFactory
import com.example.appteste.view.register.RegisterActivity
import com.example.appteste.viewmodel.EventDetailViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_event_detail.*
import kotlinx.android.synthetic.main.content_event_detail.*
import kotlinx.coroutines.Dispatchers

class EventDetailActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences

    private val viewModel: EventDetailViewModel by lazy {
        ViewModelProviders.of(
            this, getFactory(
                EventDetailViewModel(
                    MainNetwork(Dispatchers.IO)
                )
            )
        ).get(EventDetailViewModel::class.java)
    }

    companion object {
        const val EVENT_EXTRA = "eventExtra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = getSharedPreferences("com.example.appTeste", Context.MODE_PRIVATE)
        setContentView(R.layout.activity_event_detail)
        setupToolbar()
        val event = intent.getSerializableExtra(EVENT_EXTRA) as Event
        fab.setOnClickListener {
            viewModel.postCheckIn(CheckIn(event.id,
                prefs.getString(RegisterActivity.EMAIL_KEY, "") ?: "",
                prefs.getString(RegisterActivity.NAME_KEY, "") ?: ""))
        }
        setupDetails(event)
        registerObservers()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun setupDetails(event: Event) {
        text_title_detail.text = event.title
        text_when_detail.text = event.date.getDate(getString(R.string.datetime_format))
        text_price_detail.text = event.price.toString().monetize(this)
        text_description_detail.text = event.description
        Glide.with(this).load(event.image).into(toolbar_image)
    }

    private fun registerObservers() {
        viewModel.showSucess.observe(this, Observer { success ->
            if (success) {
                Snackbar.make(fab, getString(R.string.checkin_success), Snackbar.LENGTH_LONG).show()
            }
        })
        viewModel.showError.observe(this, Observer { error ->
            if (error) {
                Snackbar.make(fab, getString(R.string.checkin_error), Snackbar.LENGTH_LONG).show()
            }
        })
    }

}
