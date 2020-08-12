package br.com.nerdrapido.mvvmmockapiapp.ui.view.event

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.nerdrapido.mvvmmockapiapp.ui.view.event.fragment.EventCheckInFragment
import br.com.nerdrapido.mvvmmockapiapp.ui.view.event.fragment.EventDetailFragment
import br.com.nerdrapido.mvvmmockapiapp.ui.view.event.fragment.EventListFragment

/**
 * Created By FELIPE GUSBERTI @ 10/08/2020
 */
class EventFragmentPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    companion object {
        const val NUM_PAGES = 3
    }

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> EventListFragment()
            1 -> EventDetailFragment()
            else -> EventCheckInFragment()
        }
    }
}