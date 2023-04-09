package broz.tito.usadebt.model

import com.google.gson.annotations.SerializedName

data class RawDebt (

    @SerializedName("data") var data  : List<RawDebtEntity> = arrayListOf(),

    )

data class RawDebtEntity (

    @SerializedName("record_date") var recordDate        : String = "",
    @SerializedName("tot_pub_debt_out_amt") var totPubDebtOutAmt  : String = "",
    @SerializedName("debt_held_public_amt") var debtHeldPublicAmt : String = "",
    @SerializedName("intragov_hold_amt") var intragovHoldAmt   : String = ""

)