package broz.tito.usadebt.presentation.viewmodels

import androidx.lifecycle.*
import broz.tito.usadebt.data.debthistorydata.DebtHistoryRepositoryImpl
import broz.tito.usadebt.model.HistoryResult
import broz.tito.usadebt.data.remote.Model
import broz.tito.usadebt.domain.debthistorydomain.DebtHistoryUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HistoryFragmentViewModel(val useCase: DebtHistoryUseCase): ViewModel() {

    private val _historyResult = MutableLiveData<HistoryResult>()
    val historyResult: LiveData<HistoryResult> = _historyResult

    fun getHistory(days: String) {
        viewModelScope.launch {
            useCase(days).onEach {
                _historyResult.postValue(it)
            }.collect()
        }
    }
}