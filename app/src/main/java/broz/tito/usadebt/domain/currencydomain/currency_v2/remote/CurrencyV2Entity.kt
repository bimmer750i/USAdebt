package broz.tito.usadebt.domain.currencydomain.currency_v2.remote

import broz.tito.usadebt.domain.currencydomain.currency_v2.CommonCurrencyEntity
import java.io.Serializable

data class CurrencyV2Entity(override val currencyCode: String, override var currencyText: String, val currencyValue: String): CommonCurrencyEntity(currencyCode, currencyText),Serializable