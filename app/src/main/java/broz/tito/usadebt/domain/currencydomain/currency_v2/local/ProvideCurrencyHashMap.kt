package broz.tito.usadebt.domain.currencydomain.currency_v2.local

import broz.tito.usadebt.R

fun provideCurrenciesHashMap(): HashMap<String,Int> {
    return HashMap<String,Int>().apply {
        put("eur", R.string.eur)
        put("gbp", R.string.gbp)
        put("cad", R.string.cad)
        put("cny", R.string.cny)
        put("jpy", R.string.jpy)
        put("nzd", R.string.nzd)
        put("aud", R.string.aud)
        put("rub", R.string.rub)
        put("inr", R.string.inr)
        put("chf", R.string.chf)
    }
}