package broz.tito.usadebt.domain.currencydomain.currency_v1

import broz.tito.usadebt.data.remote.Model
import broz.tito.usadebt.data.remote.parsers.RParser
import broz.tito.usadebt.data.remote.parsers.convertToRDate
import broz.tito.usadebt.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CurrencyUseCase (val repository: CurrencyRepository, val model: Model) {

    suspend operator fun invoke(): Flow<CurrencyResult> =
        repository.getCurrency().map {
            if (it is SuccessRawCurrencyResult) {
                val raw = it.rawCurrency
                val map = parseFromRawCurrency(raw,model)
                return@map SuccessCurrencyResult(map)
            }
            else {
                return@map it
            }
        }

    fun parseFromRawCurrency(currency: RawCurrency, model: Model): HashMap<String, Currency> {
        val data = currency.data
        val currencies = HashMap<String, Currency>()
        data.forEach {
            val date: String
            var exchange_rate : String
            if (model.parser is RParser) {
                date = convertToRDate(it.recordDate)
            }
            else {
                date = it.recordDate
            }
            exchange_rate = it.exchangeRate
            val country = it.country
            currencies.put(country, Currency(exchange_rate,date))
        }
        return currencies
    }

}