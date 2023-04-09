package broz.tito.usadebt.domain.currentdebtdomain

import broz.tito.usadebt.model.DebtResult
import broz.tito.usadebt.data.remote.Model
import kotlinx.coroutines.flow.Flow

interface CurrentDebtRepository {

    suspend fun getDebt(): Flow<DebtResult>

}