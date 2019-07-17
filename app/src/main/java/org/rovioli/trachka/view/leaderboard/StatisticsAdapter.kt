package org.rovioli.trachka.view.leaderboard

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.stats_card.view.*
import org.rovioli.trachka.R
import org.rovioli.trachka.model.Currency
import org.rovioli.trachka.model.CurrencyRepository
import org.rovioli.trachka.model.Spending

val TAG = StatisticsAdapter::class.java.toString()

class StatisticsAdapter(
    private val ctx: Context,
    private val stats: List<Spending>,
    private val currencyRepo: CurrencyRepository
) : ArrayAdapter<Spending>(ctx, R.layout.stats_card) {

    private val backgroundColors = arrayOf(
        R.color.firstScore,
        R.color.secondScore,
        R.color.thirdScore
    )

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = inflater.inflate(R.layout.stats_card, parent, false)
        if (position < backgroundColors.size) {
            Log.d(TAG, "getView(position $position)")
            with(layout) {
                background = ctx.getDrawable(backgroundColors[position])
                name.text     = stats[position].username
                money.text    = stats[position].price.toString()
                currency.text = currencyRepo.getCurrencyById(stats[position].currencyId).name
                setCardTextColor(android.R.color.white)
            }
        }
        return layout
    }

    override fun getCount() = stats.size

    private fun View.setCardTextColor(id: Int) = with(this) {
        val colorId = context.resources.getColor(id)
        name.setTextColor(colorId)
        money.setTextColor(colorId)
        currency.setTextColor(colorId)
    }
}

