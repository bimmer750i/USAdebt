package broz.tito.usadebt.data.debthistorydata

import broz.tito.usadebt.data.remote.Model
import broz.tito.usadebt.domain.debthistorydomain.DebtHistoryRepository
import broz.tito.usadebt.model.HistoryResult
import kotlinx.coroutines.flow.Flow

class DebtHistoryRepositoryImpl: DebtHistoryRepository {

    override suspend fun getDebtHistory(days: String): Flow<HistoryResult> {
        return Model.getInstance().getHistory(days)
    }
}