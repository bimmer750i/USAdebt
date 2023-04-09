package broz.tito.usadebt.model

import com.google.gson.annotations.SerializedName

data class RawCurrency (

    @SerializedName("data"  ) var data  : List<RawCurrencyEntity> = arrayListOf(),

)

data class RawCurrencyEntity (

    @SerializedName("record_date"   ) var recordDate   : String = "",
    @SerializedName("country"       ) var country      : String = "",
    @SerializedName("exchange_rate" ) var exchangeRate : String = ""

)
