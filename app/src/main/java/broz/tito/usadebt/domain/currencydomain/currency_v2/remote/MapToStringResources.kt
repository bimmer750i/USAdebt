package broz.tito.usadebt.presentation

import android.content.Context
import broz.tito.usadebt.R
import broz.tito.usadebt.data.currencydata.currency_v2.CurrencyEntityRoom
import broz.tito.usadebt.domain.currencydomain.currency_v2.CommonCurrencyEntity
import broz.tito.usadebt.domain.currencydomain.currency_v2.local.ChooseCurrencyEntity
import broz.tito.usadebt.domain.currencydomain.currency_v2.remote.CurrencyV2Entity
import javax.inject.Inject

class MapToStringResources @Inject constructor(val ResMap: HashMap<String,Int>) {


        fun <T> addStringResources(context: Context, CodesList: ArrayList<T>?): ArrayList<T>
                where T: CommonCurrencyEntity
        {
            val new_list: ArrayList<T> = ArrayList()
            CodesList?.forEach {
                if (ResMap.containsKey(it.currencyCode)) {
                    val text = context.getString(ResMap.get(it.currencyCode)!!)
                    it.currencyText = text
                    new_list.add(it)
                }
            }
            return new_list
        }

}



