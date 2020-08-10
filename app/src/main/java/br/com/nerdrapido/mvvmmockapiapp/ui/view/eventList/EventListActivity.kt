package br.com.nerdrapido.mvvmmockapiapp.ui.view.eventList

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.nerdrapido.mvvmmockapiapp.R
import br.com.nerdrapido.mvvmmockapiapp.presentation.viewModel.eventList.EventListViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created By FELIPE GUSBERTI @ 09/08/2020
 */
class EventListActivity : AppCompatActivity() {

    private val viewModel: EventListViewModel by viewModel()

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
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }
        this.lifecycle.addObserver(viewModel)
    }

}