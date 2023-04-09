package broz.tito.usadebt.data.remote


import android.util.Log
import broz.tito.usadebt.model.*
import broz.tito.usadebt.data.remote.parsers.EnglishParser
import broz.tito.usadebt.data.remote.parsers.Parser
import retrofit2.Retrofit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.converter.gson.GsonConverterFactory
import broz.tito.usadebt.data.remote.parsers.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class Model {

    var parser: Parser = EnglishParser()
    val TAG = "Model"

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.fiscaldata.treasury.gov/services/api/fiscal_service/v2/accounting/od/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val baseService  = retrofit.create(BaseRemoteService::class.java)


    suspend fun getDebt(): Flow<DebtResult> = flow {
        Log.d(TAG, "getting Debt")
        emit(PendingDebtResult())
        val response = baseService.getDebt()
        if (!response.isSuccessful) {
            emit(FailureDebtResult())
        }
        else {
            response.body()?.let {
                val debt = it
                emit(SuccessRawDebtResult(debt))
            }
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getCurrency(): Flow<CurrencyResult> = flow {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.fiscaldata.treasury.gov/services/api/fiscal_service/v1/accounting/od/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service  = retrofit.create(BaseRemoteService::class.java)
        emit(PendingCurrencyResult())
        val response = service.getCurrency()
        if (!response.isSuccessful) {
            emit(FailureCurrencyResult())
        }
        else {
            response.body()?.let {
                emit(SuccessRawCurrencyResult(it))
            }
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getCurrencyV2(): Flow<CurrencyV2Result> = flow {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(CurrencyRemoteService::class.java)
        emit(PendingCurrencyV2Result())
        val response = service.getCurrency_v2()
        if (!response.isSuccessful) {
            Log.d(TAG, response.code().toString())
            emit(FailureCurrencyV2Result())
        }
        else {
            response.body()?.let {
                emit(SuccessRawCurrencyV2Result(it))
            }
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getHistory(days: String): Flow<HistoryResult> = flow {
        emit(PendingHistoryResult())
        val response = baseService.getHistory(days)
        if (!response.isSuccessful) {
            emit(FailureHistoryResult())
        }
        else {
            response.body()?.let { body ->
                val result = parseFromRawHistory(body,this@Model)
                emit(SuccessHistoryResult(result))
            }
        }
    }.flowOn(Dispatchers.IO)

    companion object {
        private lateinit var model: Model
        fun getInstance(): Model {
            return if (!this::model.isInitialized) {
                model = Model()
                model
            } else {
                model
            }
        }
    }
}