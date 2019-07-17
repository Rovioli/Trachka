package org.rovioli.trachka.presenter

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.rovioli.trachka.model.CurrencyRepository
import org.rovioli.trachka.view.expenses.CurrencyView

class CurrencyPresenter(
    private val view: CurrencyView,
    private val repository: CurrencyRepository
) {

    fun requestCurrencies() = MainScope().launch {
        view.onCurrenciesLoaded(repository.loadCurrencies())
    }

    fun getCurrency(id: Int) = repository.getCurrencyById(id + 1)

}