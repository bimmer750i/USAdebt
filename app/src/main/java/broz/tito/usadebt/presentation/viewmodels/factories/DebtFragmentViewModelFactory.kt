package broz.tito.usadebt.presentation.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import broz.tito.usadebt.domain.currentdebtdomain.CurrentDebtUseCase
import broz.tito.usadebt.presentation.viewmodels.DebtFragmentViewModel
import javax.inject.Inject

class DebtFragmentViewModelFactory @Inject constructor(val currentDebtUseCase: CurrentDebtUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DebtFragmentViewModel(currentDebtUseCase) as T
    }
}