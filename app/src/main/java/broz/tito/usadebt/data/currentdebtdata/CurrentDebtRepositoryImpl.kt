package broz.tito.usadebt.data.currentdebtdata

import android.util.Log
import broz.tito.usadebt.domain.currentdebtdomain.CurrentDebtRepository
import broz.tito.usadebt.model.DebtResult
import broz.tito.usadebt.data.remote.Model
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrentDebtRepositoryImpl @Inject constructor(val model: Model): CurrentDebtRepository {

    val TAG = "CurrentDebtRepository"

    override suspend fun getDebt(): Flow<DebtResult> {
        return model.getDebt()
    }
}