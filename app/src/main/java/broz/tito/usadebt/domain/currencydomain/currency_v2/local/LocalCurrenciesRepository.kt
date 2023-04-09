package broz.tito.usadebt.domain.currencydomain.currency_v2.local

import android.content.Context
import broz.tito.usadebt.data.currencydata.currency_v2.CurrencyEntityRoom

interface LocalCurrenciesRepository {

    suspend fun getUnselectedCurrencies(context: Context): List<CurrencyEntityRoom>?

    suspend fun setSelectedCurrencies(context: Context,itemList: List<CurrencyEntityRoom>)

    suspend fun getSelectedCurrencies(context: Context): List<CurrencyEntityRoom>?

    suspend fun setUnselectedCurrencies(context: Context,itemList: List<CurrencyEntityRoom>)

}
