package org.rovioli.trachka.view.expenses

interface UserSpendingView<E> {
    fun onExpensesLoaded(expenses: List<E>)

    fun raiseToast(message: String)
}