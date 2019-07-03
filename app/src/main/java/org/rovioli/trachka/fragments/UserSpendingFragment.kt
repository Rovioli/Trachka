package org.rovioli.trachka.fragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.dialog_add_spending.view.*
import kotlinx.android.synthetic.main.stats_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.rovioli.trachka.*

class UserSpendingFragment : Fragment() {
    private var userId = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.user_spending_fragment, container, false)
        val context = this.context!!

        userId = activity?.intent?.getIntExtra("id", 0) ?: 0

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val body = Connector.client.getData().body() ?: throw NoSuchElementException("Have no body")
                mySpending.adapter = SpendingAdapter(context, getSpending(userId, body))
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, R.string.connection_error, Toast.LENGTH_LONG).show()
            }
        }
        addButton.setOnClickListener { dialog.show() }
        return view
    }

    private val dialog: AlertDialog by lazy {
        val root = layoutInflater.inflate(R.layout.dialog_add_spending, null)
        val context = root.context
        root.dayOfWeekChooser.adapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_dropdown_item,
            week
        )
        val builder = AlertDialog.Builder(this.context!!)
        builder.setMessage(R.string.add_spending)
            .setView(root)
            .setPositiveButton(R.string.add) { _, _ -> addSpending(context, root) }
            .setNegativeButton(android.R.string.cancel) { _, _ -> }

        builder.create()
    }

    private fun addSpending(context: Context, root: View) {
        GlobalScope.launch(Dispatchers.Main) {
            Connector.client.addSpending(
                userId,
                root.dayOfWeekChooser.selectedItemId.toInt() + 1,
                root.comment.text.toString(),
                root.amount_of_money.text
                    .toString()
                    .toInt()
            )
            Toast.makeText(
                context,
                "Paid ${root.amount_of_money.text.toString().toInt()} for ${root.comment.text}!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun getSpending(userId: Int, body: List<Spending>) = body
        .filter { it.userid == userId }
        .sortedByDescending { it.dow }
}