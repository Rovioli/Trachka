package org.rovioli.trachka.view

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.rovioli.trachka.model.CurrencyRepository
import org.rovioli.trachka.view.expenses.UserSpendingFragment
import org.rovioli.trachka.view.leaderboard.StatisticsFragment
import org.rovioli.trachka.view.notimplemented.NotImplementedFragment

class MainPagerAdapter(
    manager: FragmentManager,
    currencyRepository: CurrencyRepository // TODO: oh, look at this duude
) : FragmentPagerAdapter(manager) {

    private val fragments = arrayOf(
        UserSpendingFragment(currencyRepository),
        StatisticsFragment(currencyRepository),
        NotImplementedFragment()
    )

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size
}