package org.rovioli.trachka.view.expenses

import org.rovioli.trachka.model.Currency

interface CurrencyView {
    fun onCurrenciesLoaded(currencies: List<Currency>)
}