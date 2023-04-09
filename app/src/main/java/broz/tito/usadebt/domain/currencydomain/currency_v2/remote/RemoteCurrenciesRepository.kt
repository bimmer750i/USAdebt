package broz.tito.usadebt.domain.currencydomain.currency_v2.remote

import broz.tito.usadebt.model.CurrencyV2Result
import kotlinx.coroutines.flow.Flow

interface RemoteCurrenciesRepository {

    suspend fun loadCurrencies(): Flow<CurrencyV2Result>

}