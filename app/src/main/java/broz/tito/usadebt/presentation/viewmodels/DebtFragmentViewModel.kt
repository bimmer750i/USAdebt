package broz.tito.usadebt.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.*
import broz.tito.usadebt.data.currentdebtdata.CurrentDebtRepositoryImpl
import broz.tito.usadebt.domain.currentdebtdomain.CurrentDebtUseCase
import broz.tito.usadebt.model.DebtResult
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class DebtFragmentViewModel @Inject constructor(val currentDebtUseCase: CurrentDebtUseCase): ViewModel() {
    val TAG = "ViewModel"


    private val _currentDebt = MutableLiveData<DebtResult>()
    val currentDebt: LiveData<DebtResult> = _currentDebt


    suspend fun getDebt() {
        Log.d(TAG, "Calling UseCase")
        currentDebtUseCase().onEach {
            _currentDebt.postValue(it)
        }.launchIn(viewModelScope)
    }

}

