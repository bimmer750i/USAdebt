package broz.tito.usadebt.presentation.viewmodels

import androidx.lifecycle.*
import broz.tito.usadebt.data.currencydata.currency_v1.CurrencyRepositoryImpl
import broz.tito.usadebt.model.CurrencyResult
import broz.tito.usadebt.data.remote.Model
import broz.tito.usadebt.domain.currencydomain.currency_v1.CurrencyUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CurrencyFragmentViewModel(): ViewModel() {


    /*private val useCase = CurrencyUseCase(CurrencyRepositoryImpl())
    private val _currencyResult = MutableLiveData<CurrencyResult>()
    val currencyResult: LiveData<CurrencyResult> = _currencyResult
    private val TAG = "CurrencyViewModel"

    suspend fun getCurrency() {
       useCase().onEach {
           _currencyResult.postValue(it)
       }.launchIn(viewModelScope)
    }*/

}