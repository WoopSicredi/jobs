package br.com.nerdrapido.mvvmmockapiapp.ui.view.eventList

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.nerdrapido.mvvmmockapiapp.R
import br.com.nerdrapido.mvvmmockapiapp.presentation.viewModel.eventList.EventListViewModel
import kotlinx.android.synthetic.main.activity_event_list.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created By FELIPE GUSBERTI @ 09/08/2020
 */
class EventListActivity : AppCompatActivity() {

    @VisibleForTesting
    val viewModel: EventListViewModel by viewModel()

    private val adapter = EventListAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateCall()
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        onCreateCall()
    }

    /**
     * Função com o que deve ser chamado no [onCreate].
     * Ambas assinaturas do on create devem ser sobrescritas pois se não podem ocrrer problemas em
     * reaberturas do aplicativo, desta forma, este método foi criado para evitar repetir código.
     *
     * @Suppress("DEPRECATION"): Resources.getColor(int, Theme) não suporta a versão min do sdk
     *
     */
    @SuppressLint("SourceLockedOrientationActivity")
    @Suppress("DEPRECATION")
    private fun onCreateCall() {
        @Suppress("UNCHECKED_CAST")
        setContentView(R.layout.activity_event_list)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        eventListRv.adapter = adapter
        eventListRv.layoutManager = LinearLayoutManager(this)
        registerObservers()
    }

    private fun registerObservers() {
        viewModel.eventListStateLiveData.observe(this, Observer {
            adapter.setItems(it.eventList)
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.load()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}