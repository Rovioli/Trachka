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
import org.rovioli.trachka.model.Currency
import org.rovioli.trachka.model.CurrencyRepository
import org.rovioli.trachka.model.Spending
import org.rovioli.trachka.model.ZhrachkaApi
import org.rovioli.trachka.presenter.CurrencyPresenter
import org.rovioli.trachka.presenter.ExpensesPresenter

class UserSpendingFragment(
    currencyRepository: CurrencyRepository // TODO: god damn!
) : Fragment(), UserSpendingView<Spending>, CurrencyView {
    private val expensePresenter = ExpensesPresenter(this, ZhrachkaApi.CLIENT)

    private val currencyPresenter = CurrencyPresenter(this, currencyRepository)
    private var userId = 0
    private lateinit var dialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userId = activity?.intent?.getIntExtra("id", 0) ?: 0
        val view = inflater.inflate(R.layout.user_spending_fragment, container, false)
        currencyPresenter.requestCurrencies()
        expensePresenter.requestUserExpenses(userId)
        return view
    }

    override fun raiseToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun onExpensesLoaded(expenses: List<Spending>) {
        mySpending.adapter = SpendingAdapter(context!!, expenses)
        addButton.setOnClickListener {
            if (::dialog.isInitialized) {
                dialog.show()
            }
        }
    }

    override fun onCurrenciesLoaded(currencies: List<Currency>) {
        val root = layoutInflater.inflate(R.layout.dialog_add_spending, null)
        configureSpinner(root, currencies.map { it.name })
        dialog = AlertDialog.Builder(context!!)
            .setTitle(R.string.add_spending)
            .setView(root)
            .setPositiveButton(R.string.add) { _, _ ->
                expensePresenter.addExpense(
                    userId,
                    root.amount_of_money.text.toString().toDouble(), // GOD, PLEASE, NO!!!
                    root.comment.text.toString(),
                    root.currency.selectedItemId.toInt() + 1
                    // TODO: get datetime
                )
                expensePresenter.requestUserExpenses(userId)
            }
            .setNegativeButton(android.R.string.cancel) { _, _ -> }
            .create()

    }

    private fun configureSpinner(root: View, names: List<String>) {
        ArrayAdapter(
            root.context,
            android.R.layout.simple_spinner_item,
            names
        ).also { adapter -> root.currency.adapter = adapter }
    }
}