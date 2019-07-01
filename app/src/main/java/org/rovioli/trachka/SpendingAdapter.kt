package org.rovioli.trachka

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.time.DayOfWeek

class SpendingAdapter(
    private val ctx: Context,
    private val spending: List<Spending>,
    private val showName: Boolean = false
) : ArrayAdapter<Spending>(ctx, R.layout.spending) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = inflater.inflate(R.layout.spending, parent, false)
        setVisibility(layout, position, showName)
        layout.setTo(R.id.comment, spending[position].descr)
        layout.setTo(R.id.money, spending[position].price.toString())
        return layout
    }

    override fun getCount() = spending.size

    private fun setVisibility(parent: View, position: Int, visible: Boolean) {
        val info = parent.findViewById<View>(R.id.commonInfo)
        if (visible) {

            info.visibility = View.VISIBLE
            parent.setTo(R.id.name, spending[position].username)
            parent.setTo(R.id.dayOfWeek, getDayOfWeek(spending[position].dow))
        } else {
            info.visibility = View.GONE
        }
    }
}

private fun getDayOfWeek(i: Int) = DayOfWeek.values()[i - 1].toString()

private fun View.setTo(id: Int, data: String) {
    findViewById<TextView>(id).text = data
}