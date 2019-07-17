package org.rovioli.trachka.model

class CurrencyRepository(private val client: ZhrachkaApi) {

    private var currencies: List<Currency> = arrayListOf()
    private val defaultCurrency = Currency(1, "RUB")

    fun getCurrencies() = currencies

    fun getCurrencyById(id: Int) = getCurrencies()
        .firstOrNull { it.id == id } ?: defaultCurrency

    suspend fun loadCurrencies(): List<Currency> {
        currencies = client
            .getCurrencies()
            .body() ?: arrayListOf()
        return currencies
    }
}