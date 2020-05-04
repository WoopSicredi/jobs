package com.example.appteste.view.eventdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.appteste.R
import com.example.appteste.model.Event
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_event_detail.*
import kotlinx.android.synthetic.main.content_event_detail.*

class EventDetailActivity : AppCompatActivity() {

    companion object {
        const val EVENT_EXTRA = "eventExtra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        setupDetails(intent.getSerializableExtra(EVENT_EXTRA) as Event)
    }

    private fun setupDetails(event: Event) {
        toolbar_layout.title = event.title
        Glide.with(this).load(event.image).into(toolbar_image)
    }

}
