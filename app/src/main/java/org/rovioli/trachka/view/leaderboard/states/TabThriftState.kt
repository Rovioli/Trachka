package org.rovioli.trachka.view.leaderboard.states

import android.view.View
import kotlinx.android.synthetic.main.stats_fragment.view.*
import org.rovioli.trachka.R
import org.rovioli.trachka.model.Spending
import org.rovioli.trachka.view.leaderboard.StatisticsAdapter
import org.rovioli.trachka.getString
import org.rovioli.trachka.view.ViewState

class TabThriftState(private val spending: List<Spending>) : ViewState {
    override fun select(root: View) {
        root.topTitle.text = root.getString(R.string.thrifty_top)
        root.recordList.adapter =
            StatisticsAdapter(root.context, spending.sortedBy { it.price })
    }
}