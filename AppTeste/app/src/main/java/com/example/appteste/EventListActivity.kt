package com.example.appteste

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appteste.extensions.setToolbarTitle
import com.example.appteste.network.MainNetwork
import com.example.appteste.util.getFactory
import com.example.appteste.view.event.EventAdapter
import com.example.appteste.viewmodel.EventListViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_event_detail.*
import kotlinx.android.synthetic.main.activity_event_list.*
import kotlinx.coroutines.Dispatchers

class EventListActivity : AppCompatActivity() {

    private val viewModel: EventListViewModel by lazy {
        ViewModelProviders.of(
            this, getFactory(
                EventListViewModel(
                    MainNetwork(Dispatchers.IO)
                )
            )
        ).get(EventListViewModel::class.java)
    }

    private val adapter: EventAdapter by lazy {
        EventAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_list)
        setupToolbar()
        registerObservers()
        configAdapter()
    }

    private fun setupToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar_top))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setToolbarTitle(getString(R.string.activity_list_title))
    }

    override fun onResume() {
        super.onResume()
        viewModel.getEventList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun registerObservers() {
        viewModel.eventList.observe(this, Observer { eventList ->
            if(eventList.isNullOrEmpty()) {
                return@Observer
            } else {
                adapter.addEvents(eventList)
                recycler_event_list.visibility = View.VISIBLE
            }
        })
        viewModel.showLoader.observe(this, Observer { show ->
            if(show) {
                progress_main.visibility = View.VISIBLE
            } else {
                progress_main.visibility = View.GONE
            }
        })
        viewModel.showError.observe(this, Observer { error ->
            if (error) {
                progress_main.visibility = View.GONE
            }
        })
    }

    private fun configAdapter() {
        recycler_event_list.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL, false)
        recycler_event_list.adapter = adapter
    }
}
