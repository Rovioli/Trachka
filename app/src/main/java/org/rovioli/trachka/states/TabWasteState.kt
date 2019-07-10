package org.rovioli.trachka.states

import android.view.View
import kotlinx.android.synthetic.main.stats_fragment.view.*
import org.rovioli.trachka.R
import org.rovioli.trachka.Spending
import org.rovioli.trachka.StatisticsAdapter
import org.rovioli.trachka.getString

class TabWasteState(private val spending: List<Spending>) : ViewState {
    override fun select(root: View) {
        root.topTitle.text = root.getString(R.string.wasteful_top)
        root.recordList.adapter = StatisticsAdapter(root.context, spending.sortedByDescending { it.price })
    }
}