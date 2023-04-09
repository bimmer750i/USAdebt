package broz.tito.usadebt.data.currencydata.currency_v2

import com.google.gson.annotations.SerializedName

data class RawCurrencyV2 (
    @SerializedName("date") val date: String,
    @SerializedName("usd") val usd: Map<String, Double>
)