package broz.tito.usadebt.data.debthistorydata

import broz.tito.usadebt.data.remote.Model
import broz.tito.usadebt.domain.debthistorydomain.DebtHistoryRepository
import broz.tito.usadebt.model.HistoryResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DebtHistoryRepositoryImpl @Inject constructor(val model: Model): DebtHistoryRepository {

    override suspend fun getDebtHistory(days: String): Flow<HistoryResult> {
        return model.getHistory(days)
    }
}