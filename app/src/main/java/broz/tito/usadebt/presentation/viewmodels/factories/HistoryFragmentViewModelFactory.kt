package broz.tito.usadebt.presentation.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import broz.tito.usadebt.domain.debthistorydomain.DebtHistoryUseCase
import broz.tito.usadebt.presentation.viewmodels.HistoryFragmentViewModel
import javax.inject.Inject

class HistoryFragmentViewModelFactory @Inject constructor(val debtHistoryUseCase: DebtHistoryUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HistoryFragmentViewModel(debtHistoryUseCase) as T
    }
}