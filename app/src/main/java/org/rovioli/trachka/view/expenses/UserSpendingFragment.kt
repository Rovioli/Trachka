package org.rovioli.trachka.view.expenses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.dialog_add_spending.view.*
import kotlinx.android.synthetic.main.user_spending_fragment.*
import org.rovioli.trachka.R
import org.rovioli.trachka.model.Spending
import org.rovioli.trachka.model.ZhrachkaApi
import org.rovioli.trachka.presenter.ExpensesPresenter

class UserSpendingFragment : Fragment(), UserSpendingView<Spending> {
    private val presenter = ExpensesPresenter(this, ZhrachkaApi.CLIENT)
    private var userId = 0

    private val dialog: AlertDialog by lazy {
        val root = layoutInflater.inflate(R.layout.dialog_add_spending, null)
        configureSpinner(root)
        AlertDialog.Builder(context!!)
            .setTitle(R.string.add_spending)
            .setView(root)
            .setPositiveButton(R.string.add) { _, _ ->
                presenter.addExpense(
                    userId,
                    root.amount_of_money.text.toString().toInt(), // GOD, PLEASE, NO!!!
                    root.comment.text.toString(),
                    root.currency.selectedItem.toString()
                    // TODO: get datetime
                )
                presenter.requestUserExpenses(userId)
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
        presenter.requestUserExpenses(userId)
        return view
    }

    override fun raiseToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun onExpensesLoaded(expenses: List<Spending>) {
        mySpending.adapter = SpendingAdapter(context!!, expenses)
        addButton.setOnClickListener { dialog.show() }
    }

    private fun configureSpinner(root: View) {
        ArrayAdapter.createFromResource(
            root.context,
            R.array.currencies,
            android.R.layout.simple_spinner_item
        ).also { adapter -> root.currency.adapter = adapter }
    }
}