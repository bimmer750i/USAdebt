package broz.tito.usadebt.data.remote

import broz.tito.usadebt.model.RawCurrency
import broz.tito.usadebt.model.RawDebt
import broz.tito.usadebt.model.RawHistory
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseRemoteService {

    @GET("debt_to_penny?fields=record_date,tot_pub_debt_out_amt,debt_held_public_amt,intragov_hold_amt&page[size]=1&sort=-record_date&format=json")
    suspend fun getDebt(): Response<RawDebt>

    @GET("rates_of_exchange?fields=record_date,country,exchange_rate&sort=-record_date&filter=country:in:(China,Euro%20Zone,Japan,United%20Kingdom,Canada,Switzerland)&page[size]=6&format=json")
    suspend fun getCurrency(): Response<RawCurrency>

    @GET("debt_to_penny?fields=record_date,tot_pub_debt_out_amt&sort=-record_date")
    suspend fun getHistory(@Query("page[size]") days: String): Response<RawHistory>
}