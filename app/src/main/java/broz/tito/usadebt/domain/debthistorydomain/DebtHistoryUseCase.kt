package broz.tito.usadebt.domain.debthistorydomain

import broz.tito.usadebt.data.remote.Model
import broz.tito.usadebt.data.remote.parsers.parseFromRawHistory
import broz.tito.usadebt.model.HistoryResult
import broz.tito.usadebt.model.SuccessHistoryResult
import broz.tito.usadebt.model.SuccessRawHistoryResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DebtHistoryUseCase @Inject constructor(val repository: DebtHistoryRepository, val model: Model) {

    suspend operator fun invoke(days: String) : Flow<HistoryResult> =
        repository.getDebtHistory(days).map {
            if (it is SuccessRawHistoryResult) {
                val raw = it.rawHistory
                val list = parseFromRawHistory(raw, model)
                return@map SuccessHistoryResult(list)
            }
            else {
                return@map it
            }
        }.flowOn(Dispatchers.IO)

}