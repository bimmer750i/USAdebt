package broz.tito.usadebt.domain.currentdebtdomain

import android.util.Log
import broz.tito.usadebt.data.remote.Model
import broz.tito.usadebt.model.*
import broz.tito.usadebt.data.remote.parsers.RParser
import broz.tito.usadebt.data.remote.parsers.convertToRDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CurrentDebtUseCase @Inject constructor(val repository: CurrentDebtRepository) {

    val TAG = "UseCase"

    suspend operator fun invoke(): Flow<DebtResult> =
        repository.getDebt().map {
            Log.d(TAG, "Calling repository")
            if (it is SuccessRawDebtResult) {
                val debt = mapToDebt(it.rawDebt, Model.getInstance())
                return@map SuccessDebtResult(debt)
            } else {
                return@map it
            }
        }.flowOn(Dispatchers.IO)

    fun mapToDebt(debt: RawDebt, model: Model): Debt {
        val data = debt.data.first()
        var date = data.recordDate
        val rdate = convertToRDate(date)
        var text: String
        var extended_text: String
        if (model.parser is RParser) {
            date = rdate
            text = model.parser.parse("",date,data.totPubDebtOutAmt)
            extended_text =text + "\n" + model.parser.parse("Внутренний долг:",date,data.intragovHoldAmt) + "\n" + model.parser.parse("Публичный долг:",date,data.debtHeldPublicAmt)
        }
        else {
            text = model.parser.parse("",date,data.totPubDebtOutAmt)
            extended_text = text + "\n" + model.parser.parse("Intragovernmental holdings:",date,data.intragovHoldAmt) + "\n" + model.parser.parse("Public held debt:",date,data.debtHeldPublicAmt)
        }
        return Debt(date,text, extended_text)
    }

}