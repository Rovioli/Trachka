package org.rovioli.trachka.view.leaderboard.states

import android.view.View
import kotlinx.android.synthetic.main.stats_fragment.view.*
import org.rovioli.trachka.R
import org.rovioli.trachka.model.Spending
import org.rovioli.trachka.view.leaderboard.StatisticsAdapter
import org.rovioli.trachka.getString
import org.rovioli.trachka.view.ViewState

class TabWasteState(private val spending: List<Spending>) : ViewState {
    override fun select(root: View) {
        root.topTitle.text = root.getString(R.string.wasteful_top)
        root.recordList.adapter = StatisticsAdapter(
            root.context,
            spending.sortedByDescending { it.price })
    }
}