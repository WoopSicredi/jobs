package br.com.nerdrapido.mvvmmockapiapp.ui.view.eventList

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.nerdrapido.mvvmmockapiapp.R
import br.com.nerdrapido.mvvmmockapiapp.presentation.enums.ViewStateEnum
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

    /**
     * Diálogo genérico para apresentação de erros
     */
    private val errorDialog: AlertDialog by lazy {
        return@lazy AlertDialog.Builder(this, R.style.ThemeOverlay_MaterialComponents_Dialog)
            .setPositiveButton(
                R.string.activity_error_positive_button
            ) { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
                viewModel.onTryAgainClick()
            }
            .create()
    }

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

    /**
     * @suppress nunca vai ser nulo
     */
    @Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA")
    private fun registerObservers() {
        viewModel.getEventList().observe(this, Observer { adapter.setItems(it) })
        viewModel.getViewState().observe(this, Observer {
            when (it) {
                ViewStateEnum.LOADING -> eventListPb.show()
                ViewStateEnum.SUCCESS -> eventListPb.hide()
                ViewStateEnum.FAILED -> {
                    eventListPb.hide()
                    showApiErrorResponse()
                }
            }
        })
    }

    /**
     * Mostra diálogo com erro de carregamento da API.
     */
    private fun showApiErrorResponse() {
        errorDialog.setTitle(getString(R.string.activity_error_message_api_title))
        errorDialog.setMessage(getString(R.string.activity_error_message_api_message))
        errorDialog.show()
    }
}
