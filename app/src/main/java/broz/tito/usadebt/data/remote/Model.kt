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
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class Model @Inject constructor(
            @param:Named("base")
            val baseService: BaseRemoteService,
            @param:Named("currency")
            val currencyV1Service: BaseRemoteService,
            val currencyV2Service: CurrencyRemoteService) {

    val TAG = "Model"
    var parser: Parser = EnglishParser()

    suspend fun getDebt(): Flow<DebtResult> = flow {
        Log.d(TAG, "getting Debt")
        emit(PendingDebtResult())
        try {
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
        }
        catch (e: Exception) {
            emit(FailureDebtResult())
        }
    }.flowOn(Dispatchers.IO)

    // Currency V1, mentioned in domain/data layer as currency_V1
    suspend fun getCurrency(): Flow<CurrencyResult> = flow {
        emit(PendingCurrencyResult())
       try {
           val response = currencyV1Service.getCurrency()
           if (!response.isSuccessful) {
               emit(FailureCurrencyResult())
           }
           else {
               response.body()?.let {
                   emit(SuccessRawCurrencyResult(it))
               }
           }
       }
       catch (e: Exception) {
           emit(FailureCurrencyResult())
       }
    }.flowOn(Dispatchers.IO)

    suspend fun getCurrencyV2(): Flow<CurrencyV2Result> = flow {
        emit(PendingCurrencyV2Result())
        try {
            val response = currencyV2Service.getCurrency_v2()
            if (!response.isSuccessful) {
                Log.d(TAG, response.code().toString())
                emit(FailureCurrencyV2Result())
            }
            else {
                response.body()?.let {
                    emit(SuccessRawCurrencyV2Result(it))
                }
            }
        }
        catch (e: Exception) {
            emit(FailureCurrencyV2Result())
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getHistory(days: String): Flow<HistoryResult> = flow {
        emit(PendingHistoryResult())
        try {
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
        }
        catch (e: Exception) {
            emit(FailureHistoryResult())
        }
    }.flowOn(Dispatchers.IO)

}