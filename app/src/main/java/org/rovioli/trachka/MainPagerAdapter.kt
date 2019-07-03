package org.rovioli.trachka

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.rovioli.trachka.fragments.NotImplementedFragment
import org.rovioli.trachka.fragments.StatisticsFragment
import org.rovioli.trachka.fragments.UserSpendingFragment

class MainPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private val fragments = arrayOf(
        UserSpendingFragment(),
        StatisticsFragment(),
        NotImplementedFragment()
    )

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size
}