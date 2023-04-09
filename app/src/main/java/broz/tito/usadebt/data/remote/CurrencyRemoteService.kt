package broz.tito.usadebt.data.remote

import broz.tito.usadebt.data.currencydata.currency_v2.RawCurrencyV2
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyRemoteService {

    @GET("latest/currencies/usd.json")
    suspend fun getCurrency_v2(): Response<RawCurrencyV2>

}