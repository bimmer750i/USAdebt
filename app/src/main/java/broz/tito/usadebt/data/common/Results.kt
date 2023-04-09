package broz.tito.usadebt.model

import broz.tito.usadebt.data.currencydata.currency_v2.RawCurrencyV2
import broz.tito.usadebt.domain.currencydomain.currency_v2.remote.CurrencyV2DomainEntity
import broz.tito.usadebt.domain.currentdebtdomain.Debt
import java.io.Serializable

open class DebtResult

class SuccessRawDebtResult(val rawDebt: RawDebt): DebtResult()

class SuccessDebtResult(val debt: Debt): DebtResult()

class FailureDebtResult: DebtResult()

class PendingDebtResult: DebtResult()




open class CurrencyResult

class SuccessRawCurrencyResult(val rawCurrency: RawCurrency): CurrencyResult()

class SuccessCurrencyResult(val currencies: HashMap<String,Currency>): CurrencyResult()

class FailureCurrencyResult: CurrencyResult()

class PendingCurrencyResult: CurrencyResult()

data class Currency(val value: String,val date: String): Serializable



open class CurrencyV2Result

class SuccessRawCurrencyV2Result(val rawcurrency2: RawCurrencyV2): CurrencyV2Result()

class SuccessCurrencyV2Result(val currency: CurrencyV2DomainEntity): CurrencyV2Result()

class FailureCurrencyV2Result: CurrencyV2Result()

class PendingCurrencyV2Result: CurrencyV2Result()


open class HistoryResult

class SuccessRawHistoryResult(val rawHistory: RawHistory): HistoryResult()

class SuccessHistoryResult(val debtarray: ArrayList<String>): HistoryResult()

class FailureHistoryResult: HistoryResult()

class PendingHistoryResult: HistoryResult()


