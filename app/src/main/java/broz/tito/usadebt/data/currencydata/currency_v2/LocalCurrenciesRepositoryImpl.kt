package broz.tito.usadebt.data.currencydata.currency_v2

import android.content.Context
import broz.tito.usadebt.data.localstorage.CurrencyRoomDataBase
import broz.tito.usadebt.domain.currencydomain.currency_v2.local.LocalCurrenciesRepository
import kotlinx.coroutines.runBlocking

class LocalCurrenciesRepositoryImpl: LocalCurrenciesRepository {

    val TAG = "Repository"

    override suspend fun getUnselectedCurrencies(context: Context): List<CurrencyEntityRoom>? {
        return CurrencyRoomDataBase.getInstance(context)?.currencyDao()?.getCurrencies(false)
    }

    override suspend fun setSelectedCurrencies(
        context: Context,
        itemList: List<CurrencyEntityRoom>
    ) {
        CurrencyRoomDataBase.getInstance(context)?.currencyDao()?.updateAll(itemList)
    }

    override suspend fun getSelectedCurrencies(context: Context): List<CurrencyEntityRoom>? {
        return CurrencyRoomDataBase.getInstance(context)?.currencyDao()?.getCurrencies(true)
    }

    override suspend fun setUnselectedCurrencies(
        context: Context,
        itemList: List<CurrencyEntityRoom>
    ) {
        CurrencyRoomDataBase.getInstance(context)?.currencyDao()?.updateAll(itemList)
    }
}