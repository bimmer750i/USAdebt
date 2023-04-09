package broz.tito.usadebt.model

import com.google.gson.annotations.SerializedName

data class RawHistory (

    @SerializedName("data"  ) var data  : ArrayList<RawHistoryEntity> = arrayListOf(),

)

data class RawHistoryEntity (

    @SerializedName("record_date"          ) var recordDate       : String = "",
    @SerializedName("tot_pub_debt_out_amt" ) var totPubDebtOutAmt : String = ""

)