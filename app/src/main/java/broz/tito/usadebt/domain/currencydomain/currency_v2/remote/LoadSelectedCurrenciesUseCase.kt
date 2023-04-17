package broz.tito.usadebt.domain.currencydomain.currency_v2.remote

import android.content.Context
import broz.tito.usadebt.data.remote.Model
import broz.tito.usadebt.data.remote.parsers.RParser
import broz.tito.usadebt.data.remote.parsers.convertToRDate
import broz.tito.usadebt.model.CurrencyV2Result
import broz.tito.usadebt.model.SuccessCurrencyV2Result
import broz.tito.usadebt.model.SuccessRawCurrencyV2Result
import broz.tito.usadebt.presentation.MapToStringResources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class LoadSelectedCurrenciesUseCase @Inject constructor(private val repository: RemoteCurrenciesRepository, private val mapToStringResources: MapToStringResources, private val model: Model) {

    val TAG = "LoadCurrenciesUseCase"

    suspend operator fun invoke(context: Context,currencyCodesList: ArrayList<String>): Flow<CurrencyV2Result> {
        return repository.loadCurrencies().map { result ->
            if (result is SuccessRawCurrencyV2Result) {
                var date: String
                if (model.parser is RParser) {
                    date = convertToRDate(result.rawcurrency2.date)
                }
                else {
                    date = result.rawcurrency2.date
                }
                val list = ArrayList<CurrencyV2Entity>()
                currencyCodesList.forEach {
                    val code = it
                    val text = ""
                    val value = result.rawcurrency2.usd.get(code).toString()
                    list.add(CurrencyV2Entity(code,text,value))
                }
                mapToStringResources.addStringResources(context,list)
                return@map SuccessCurrencyV2Result(CurrencyV2DomainEntity(date,list))
            }
            else {
                return@map result
            }
        }.flowOn(Dispatchers.IO)
    }

}