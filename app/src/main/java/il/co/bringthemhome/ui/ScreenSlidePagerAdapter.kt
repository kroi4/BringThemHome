package il.co.bringthemhome.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import il.co.bringthemhome.ui.screens.ActivitiesFragment
import il.co.bringthemhome.ui.screens.ReleasedFragment
import il.co.bringthemhome.ui.screens.SearchFragment

class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private val NUM_PAGES = 3

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ActivitiesFragment()
            1 -> ReleasedFragment()
            2 -> SearchFragment()
            else -> SearchFragment()
        }
    }
}
