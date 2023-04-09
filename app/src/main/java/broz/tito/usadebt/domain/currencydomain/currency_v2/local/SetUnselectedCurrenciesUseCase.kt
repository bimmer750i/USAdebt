package broz.tito.usadebt.domain.currencydomain.currency_v2.local

import android.content.Context
import broz.tito.usadebt.data.currencydata.currency_v2.CurrencyEntityRoom
import javax.inject.Inject

class SetUnselectedCurrenciesUseCase @Inject constructor(val repository: LocalCurrenciesRepository) {

    suspend operator fun invoke(context: Context, itemList: ArrayList<CurrencyEntityRoom>) {
        repository.setUnselectedCurrencies(context, itemList)
    }

}