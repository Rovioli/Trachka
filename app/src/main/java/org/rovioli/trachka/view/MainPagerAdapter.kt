package org.rovioli.trachka.view

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.rovioli.trachka.view.notimplemented.NotImplementedFragment
import org.rovioli.trachka.view.leaderboard.StatisticsFragment
import org.rovioli.trachka.view.expenses.UserSpendingFragment

class MainPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private val fragments = arrayOf(
        UserSpendingFragment(),
        StatisticsFragment(),
        NotImplementedFragment()
    )

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size
}