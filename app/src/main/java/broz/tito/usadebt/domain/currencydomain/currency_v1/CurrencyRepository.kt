package broz.tito.usadebt.domain.currencydomain.currency_v1

import broz.tito.usadebt.data.remote.Model
import broz.tito.usadebt.model.CurrencyResult
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    val model: Model
    suspend fun getCurrency(): Flow<CurrencyResult>
}