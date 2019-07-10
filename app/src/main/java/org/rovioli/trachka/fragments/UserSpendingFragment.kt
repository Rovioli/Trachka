package org.rovioli.trachka.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.dialog_add_spending.view.*
import kotlinx.android.synthetic.main.user_spending_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.rovioli.trachka.*

class UserSpendingFragment : Fragment() {
    private var userId = 0

    private val dialog: AlertDialog by lazy {
        val root = layoutInflater.inflate(R.layout.dialog_add_spending, null)
        val context = root.context

        AlertDialog.Builder(this.context!!)
            .setTitle(R.string.add_spending)
            .setView(root)
            .setPositiveButton(R.string.add) { _, _ ->
                addSpending(context, root)
                attemptShowContent(context)
            }
            .setNegativeButton(android.R.string.cancel) { _, _ -> }
            .create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.user_spending_fragment, container, false)
        userId = activity?.intent?.getIntExtra("id", 0) ?: 0
        attemptShowContent(context!!)
        return view
    }

    private fun addSpending(context: Context, root: View) {
        GlobalScope.launch(Dispatchers.Main) {
            Connector.client.addSpending(
                userId,
                currentTimeSeconds(),
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

    private fun attemptShowContent(context: Context) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                showContent(context)
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, R.string.connection_error, Toast.LENGTH_LONG).show()
            }
        }
    }

    private suspend fun showContent(context: Context) {
        val body = Connector.client.getData().body() ?: throw NoSuchElementException("Have no body")
        mySpending.adapter = SpendingAdapter(context, getSpending(userId, body))
        addButton.setOnClickListener { dialog.show() }
    }

    private fun getSpending(userId: Int, body: List<Spending>) = body
        .filter { it.userid == userId }
        .sortedByDescending { it.dow }
}