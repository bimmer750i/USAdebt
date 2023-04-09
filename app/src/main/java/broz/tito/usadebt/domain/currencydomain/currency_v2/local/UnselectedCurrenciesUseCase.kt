package broz.tito.usadebt.domain.currencydomain.currency_v2.local

import android.content.Context
import broz.tito.usadebt.R
import broz.tito.usadebt.data.currencydata.currency_v2.CurrencyEntityRoom
import broz.tito.usadebt.presentation.MapToStringResources
import javax.inject.Inject

class UnselectedCurrenciesUseCase @Inject constructor(val context: Context, val repository: LocalCurrenciesRepository, val mapToStringResources: MapToStringResources) {

    suspend operator fun invoke(): List<ChooseCurrencyEntity>? {
        val list = repository.getUnselectedCurrencies(context)
        return mapToStringResources.addStringResources(context,list?.map { ChooseCurrencyEntity(it.id,it.currencyCode,"",it.isSelected)} as ArrayList<ChooseCurrencyEntity>)
    }

}