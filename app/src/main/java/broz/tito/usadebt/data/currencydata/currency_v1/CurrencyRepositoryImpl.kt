package broz.tito.usadebt.data.currencydata.currency_v1

import broz.tito.usadebt.data.remote.Model
import broz.tito.usadebt.domain.currencydomain.currency_v1.CurrencyRepository
import broz.tito.usadebt.model.CurrencyResult
import kotlinx.coroutines.flow.Flow

class CurrencyRepositoryImpl: CurrencyRepository {
    override val model = Model.getInstance()

    override suspend fun getCurrency(): Flow<CurrencyResult> {
        return model.getCurrency()
    }
}