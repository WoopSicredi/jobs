package br.com.nerdrapido.mvvmmockapiapp.ui.view.event

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import br.com.nerdrapido.mvvmmockapiapp.R
import br.com.nerdrapido.mvvmmockapiapp.presentation.enums.ViewStateEnum
import br.com.nerdrapido.mvvmmockapiapp.presentation.viewModel.event.EventViewModel
import kotlinx.android.synthetic.main.activity_event.*
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * Created By FELIPE GUSBERTI @ 09/08/2020
 */
class EventActivity : FragmentActivity() {

    private val viewModel: EventViewModel by viewModel()

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
        setContentView(R.layout.activity_event)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        eventContainerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        eventContainerVp.adapter = EventFragmentPagerAdapter(this)
        registerObservers()
    }

    /**
     * @suppress nunca vai ser nulo
     */
    @Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA")
    private fun registerObservers() {
        viewModel.getViewState().observe(this, Observer {
            if (it == ViewStateEnum.LOADING) {
                eventPb.show()
            } else {
                eventPb.hide()
            }
            if (it == ViewStateEnum.FAILED) {
                showApiErrorResponse()
            }
        })
        viewModel.getEventSelected().observe(this, Observer {
            eventContainerVp.currentItem = 1
        })
    }

    override fun onBackPressed() {
        if (eventContainerVp.currentItem == 0) {
            super.onBackPressed()
        } else {
            eventContainerVp.currentItem = eventContainerVp.currentItem - 1
        }
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
