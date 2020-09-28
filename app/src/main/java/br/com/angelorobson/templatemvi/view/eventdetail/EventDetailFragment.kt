package br.com.angelorobson.templatemvi.view.eventdetail

import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.navArgs
import br.com.angelorobson.templatemvi.R
import br.com.angelorobson.templatemvi.databinding.FragmentEventDetailBinding
import br.com.angelorobson.templatemvi.model.domains.Event
import br.com.angelorobson.templatemvi.view.getViewModel
import br.com.angelorobson.templatemvi.view.utils.BindingFragment
import br.com.angelorobson.templatemvi.view.utils.setVisibleOrGone
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_event_detail.*


class EventDetailFragment : BindingFragment<FragmentEventDetailBinding>() {

    private var dialog: Dialog? = null
    private val args: EventDetailFragmentArgs by navArgs()
    private val mCompositeDisposable = CompositeDisposable()
    private val eventShareClickPublisherSubject = PublishSubject.create<Event>()
    private val checkInClickPublisherSubject = PublishSubject.create<Boolean>()

    private var nameEdit: EditText? = null
    private var emailEdit: EditText? = null
    private var yesBtn: CircularProgressButton? = null

    override fun getLayoutResId(): Int = R.layout.fragment_event_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupOnBackPressed()

        val disposable = Observable.mergeArray(
                eventShareClickPublisherSubject.map {
                    ShareEventClickedIntent(it)
                },
                checkInClickPublisherSubject.map {
                    val event = binding.item as Event
                    CheckInEventClickedIntent(
                            event = event,
                            emailEt = emailEdit,
                            nameEt = nameEdit,
                            confirmButton = yesBtn,
                            dialog = dialog)
                }
        )
                .compose(getViewModel(EventDetailViewModel::class).init(InitialIntent(args.event)))
                .subscribe(
                        {
                            when (it.eventDetailResult) {
                                is EventDetailResult.EventDetailLoaded -> {
                                    binding.item = it.eventDetailResult.event
                                    event_detail_progress_horizontal.setVisibleOrGone(false)
                                }
                                is EventDetailResult.Error -> {
                                    event_detail_progress_horizontal.setVisibleOrGone(false)
                                }
                            }
                        },
                        {
                            event_detail_progress_horizontal.setVisibleOrGone(false)
                        }
                )

        mCompositeDisposable.add(disposable)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_event_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_check_in -> {
                showDialog()
            }
            R.id.action_share -> {
                val event = binding.item as Event
                eventShareClickPublisherSubject.onNext(event)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showDialog() {
        dialog = Dialog(requireContext()).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(R.layout.check_in_dialog)

            val window = window
            window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

            nameEdit = findViewById(R.id.txt_name)
            emailEdit = findViewById(R.id.txt_email)
            yesBtn = findViewById(R.id.btn_confirm_check_in)
            val closeBtn = findViewById<ImageButton>(R.id.close_button)

            yesBtn?.setOnClickListener {
                yesBtn?.startAnimation()
                checkInClickPublisherSubject.onNext(true)
            }
            closeBtn.setOnClickListener {
                dismiss()
            }

            show()
        }

    }

    private fun setupOnBackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        })
    }

    override fun onDestroy() {
        mCompositeDisposable.clear()
        super.onDestroy()
    }
}