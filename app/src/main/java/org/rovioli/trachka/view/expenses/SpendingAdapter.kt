package org.rovioli.trachka.view.expenses

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.android.synthetic.main.spending.view.*
import org.rovioli.trachka.inflate
import org.rovioli.trachka.model.Spending
import  org.rovioli.trachka.view.expenses.SpendingAdapter.SpendingHolder

class SpendingAdapter(
    private val itemId: Int,
    private val expenses: List<Spending>
) : Adapter<SpendingHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SpendingHolder(parent.inflate(itemId))

    override fun onBindViewHolder(holder: SpendingHolder, position: Int) = holder.bind(position)

    override fun getItemCount() = expenses.size


    inner class SpendingHolder(private val view: View) : ViewHolder(view) {
        fun bind(position: Int) = with(expenses[position]) {
            view.name.text      = username
            view.dayOfWeek.text = downame
            view.comment.text   = descr
        }
    }
}
