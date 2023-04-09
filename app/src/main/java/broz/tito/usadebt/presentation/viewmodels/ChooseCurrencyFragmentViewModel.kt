package broz.tito.usadebt.presentation.viewmodels

import androidx.lifecycle.*
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import broz.tito.usadebt.R
import broz.tito.usadebt.data.currencydata.currency_v2.LocalCurrenciesRepositoryImpl
import broz.tito.usadebt.data.currencydata.currency_v2.CurrencyEntityRoom
import broz.tito.usadebt.domain.currencydomain.currency_v2.local.ChooseCurrencyEntity
import broz.tito.usadebt.domain.currencydomain.currency_v2.local.SetSelectedCurrenciesUseCase
import broz.tito.usadebt.domain.currencydomain.currency_v2.local.UnselectedCurrenciesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChooseCurrencyFragmentViewModel @Inject constructor(val unselectedCurrenciesUseCase: UnselectedCurrenciesUseCase,
                                                          val setSelectedCurrenciesUseCase: SetSelectedCurrenciesUseCase) :ViewModel() {

    private val TAG = "ChooseCurrencyViewModel"

    private val _unselected = MutableLiveData<ArrayList<ChooseCurrencyEntity>>()
    val unSelectedCurrenciesLiveData: LiveData<ArrayList<ChooseCurrencyEntity>> = _unselected



    suspend fun getUnselectedCurrencies(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = async {
                return@async unselectedCurrenciesUseCase()
            }.await()
            Log.d(TAG,"Unselected Currency List: "+list.toString())
            _unselected.postValue(list as ArrayList<ChooseCurrencyEntity>)
        }
    }

    suspend fun setSelectedCurrencies(context: Context,itemList: ArrayList<CurrencyEntityRoom>) {
        viewModelScope.launch(Dispatchers.IO) {
            setSelectedCurrenciesUseCase(context, itemList)
        }
    }




}