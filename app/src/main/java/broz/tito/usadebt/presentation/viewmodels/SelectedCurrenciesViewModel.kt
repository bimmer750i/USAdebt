package broz.tito.usadebt.presentation.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import broz.tito.usadebt.data.currencydata.currency_v2.CurrencyEntityRoom
import broz.tito.usadebt.data.currencydata.currency_v2.RemoteCurrenciesRepositoryImpl
import broz.tito.usadebt.data.currencydata.currency_v2.LocalCurrenciesRepositoryImpl
import broz.tito.usadebt.domain.currencydomain.currency_v2.local.GetSelectedCurrenciesUseCase
import broz.tito.usadebt.domain.currencydomain.currency_v2.local.SetUnselectedCurrenciesUseCase
import broz.tito.usadebt.domain.currencydomain.currency_v2.remote.LoadSelectedCurrenciesUseCase
import broz.tito.usadebt.model.CurrencyV2Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SelectedCurrenciesViewModel(
    val getSelectedCurrenciesUseCase: GetSelectedCurrenciesUseCase,
    val loadSelectedCurrenciesUseCase: LoadSelectedCurrenciesUseCase,
    val setUnselectedCurrenciesUseCase: SetUnselectedCurrenciesUseCase): ViewModel() {

    val TAG = "SelectedCurrencyViewModel"


    private val _currencyList = MutableLiveData<CurrencyV2Result>()
    val currencyList: LiveData<CurrencyV2Result> = _currencyList

    fun loadCurrencies(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val selected_currency_list = async { getSelectedCurrenciesUseCase.invoke(context)?.map { it.currencyCode } }.await()
            loadSelectedCurrenciesUseCase(context, selected_currency_list as ArrayList<String>).onEach {
                _currencyList.postValue(it)
            }.collect()
        }
    }

    suspend fun setUnSelectedCurrencies(context: Context,itemList: ArrayList<String>) {
        var selected = viewModelScope.async(Dispatchers.IO) {
                getSelectedCurrenciesUseCase(context)
        }.await() as ArrayList<CurrencyEntityRoom>
        selected = selected.filter { itemList.contains(it.currencyCode) } as ArrayList<CurrencyEntityRoom>
        selected.forEach { it.isSelected = false }
        Log.d(TAG, "setUnSelectedCurrencies: ${selected}")
        viewModelScope.launch(Dispatchers.IO) {
            setUnselectedCurrenciesUseCase(context,selected)
        }
    }

}