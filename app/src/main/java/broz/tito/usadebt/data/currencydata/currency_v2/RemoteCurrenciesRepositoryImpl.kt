package broz.tito.usadebt.data.currencydata.currency_v2

import broz.tito.usadebt.data.remote.Model
import broz.tito.usadebt.domain.currencydomain.currency_v2.remote.RemoteCurrenciesRepository
import broz.tito.usadebt.model.CurrencyV2Result
import kotlinx.coroutines.flow.Flow

class RemoteCurrenciesRepositoryImpl: RemoteCurrenciesRepository {
    override suspend fun loadCurrencies(): Flow<CurrencyV2Result> {
        return Model.getInstance().getCurrencyV2()
    }
}