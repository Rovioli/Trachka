package org.rovioli.trachka

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.stats_card.view.*

class StatisticsAdapter(
    private val ctx: Context,
    private val stats: List<Spending>
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
            with(layout) {
                background = ctx.getDrawable(backgroundColors[position])
                name.text = stats[position].username
                money.text = stats[position].price.toString()
                currency.text = stats[position].cur
                setCardTextColor(android.R.color.white)
            }
        } else {
            layout.setCardTextColor(android.R.color.black)
        }
        return layout
    }

    override fun getCount() = stats.size

    private fun View.setCardTextColor(id: Int) = with(this) {
        val colorId = context.resources.getColor(id)
        name.setTextColor(colorId)
        currency.setTextColor(colorId)
        money.setTextColor(colorId)
    }
}

