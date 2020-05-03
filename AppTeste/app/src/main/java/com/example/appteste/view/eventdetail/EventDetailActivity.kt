package com.example.appteste.view.eventdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.appteste.R
import com.example.appteste.model.Event
import kotlinx.android.synthetic.main.activity_event_detail.*

class EventDetailActivity : AppCompatActivity() {

    companion object {
        const val EVENT_EXTRA = "eventExtra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)
        setupBackButton()
        setupDetails(intent.getSerializableExtra(EVENT_EXTRA) as Event)
    }

    private fun setupBackButton() {
        button_back_detail.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupDetails(event: Event) {
        Glide.with(this).load(event.image).into(image_cover_reading_detail)
    }

}
