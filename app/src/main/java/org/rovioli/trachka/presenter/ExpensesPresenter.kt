package org.rovioli.trachka.presenter

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.rovioli.trachka.currentTimeSeconds
import org.rovioli.trachka.model.Spending
import org.rovioli.trachka.model.ZhrachkaApi
import org.rovioli.trachka.view.expenses.UserSpendingView

class ExpensesPresenter(
    private val view: UserSpendingView<Spending>,
    private val client: ZhrachkaApi
) {

    fun requestUserExpenses(userId: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            val body = client.getData(userId).body()
                ?: throw NoSuchElementException("Have no body")
            view.onExpensesLoaded(body.sortedByDescending { it.timeStamp })
        }
    }

    fun addExpense(userId: Int, money: Double, comment: String, currency: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            client.addSpending(userId, currentTimeSeconds(), comment, money, currency)
            view.raiseToast("Done!")
        }
    }
}