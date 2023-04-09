package broz.tito.usadebt.domain.debthistorydomain

import broz.tito.usadebt.data.remote.Model
import broz.tito.usadebt.model.HistoryResult
import kotlinx.coroutines.flow.Flow

interface DebtHistoryRepository {

    suspend fun getDebtHistory(days: String): Flow<HistoryResult>
}