package br.com.nerdrapido.mvvmmockapiapp.ui.view.event.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import br.com.nerdrapido.mvvmmockapiapp.R
import br.com.nerdrapido.mvvmmockapiapp.presentation.enums.ViewStateEnum
import br.com.nerdrapido.mvvmmockapiapp.presentation.model.CheckIn
import br.com.nerdrapido.mvvmmockapiapp.presentation.model.Event
import br.com.nerdrapido.mvvmmockapiapp.presentation.viewModel.event.EventViewModel
import kotlinx.android.synthetic.main.fragment_event_check_in.*

/**
 * Created By FELIPE GUSBERTI @ 11/08/2020
 */
class EventCheckInFragment : Fragment() {

    private val viewModel: EventViewModel by activityViewModels()

    private lateinit var event: Event

    private lateinit var checkIn: CheckIn

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_event_check_in, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentEventCheckInBt.setOnClickListener {
            viewModel.onCheckInRequested(fragmentEventCheckInNameEt.text.toString(), fragmentEventCheckInEmailEt.text.toString())
        }
        viewModel.getEventSelected().observe(viewLifecycleOwner, Observer {
            event = it
        })
        viewModel.getViewState().observe(viewLifecycleOwner, Observer {
            fragmentEventCheckInBt.isEnabled = it == ViewStateEnum.SUCCESS
        })
        viewModel.getEventCheckInNotValid().observe(viewLifecycleOwner, Observer {
            if (it == EventViewModel.FieldRuleEnum.NAME_MISSING) {
                setErrorOnEditText(fragmentEventCheckInNameEt)
            }
            if (it == EventViewModel.FieldRuleEnum.EMAIL_MISSING) {
                setErrorOnEditText(fragmentEventCheckInEmailEt)
            }
        })
    }

    private fun setErrorOnEditText(view: AppCompatEditText) {
        view.isFocusable = true
        view.requestFocus()
        view.error = "Obrigatório"
    }
}
