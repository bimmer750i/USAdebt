package broz.tito.usadebt.domain.currencydomain.currency_v2.local

import broz.tito.usadebt.domain.currencydomain.currency_v2.CommonCurrencyEntity

data class ChooseCurrencyEntity(var id: Int,
                                override val currencyCode: String, override var currencyText: String, val isSelected: Boolean): CommonCurrencyEntity(currencyCode, currencyText)