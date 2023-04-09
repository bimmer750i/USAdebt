package broz.tito.usadebt.data.currentdebtdata

import android.util.Log
import broz.tito.usadebt.domain.currentdebtdomain.CurrentDebtRepository
import broz.tito.usadebt.model.DebtResult
import broz.tito.usadebt.data.remote.Model
import kotlinx.coroutines.flow.Flow

class CurrentDebtRepositoryImpl: CurrentDebtRepository {

    val TAG = "CurrentDebtRepository"

    override suspend fun getDebt(): Flow<DebtResult> {
        return Model.getInstance().getDebt()
    }
}