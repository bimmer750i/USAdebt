package broz.tito.usadebt.presentation.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import broz.tito.usadebt.domain.currencydomain.currency_v2.local.GetSelectedCurrenciesUseCase
import broz.tito.usadebt.domain.currencydomain.currency_v2.local.SetUnselectedCurrenciesUseCase
import broz.tito.usadebt.domain.currencydomain.currency_v2.remote.LoadSelectedCurrenciesUseCase
import broz.tito.usadebt.presentation.viewmodels.SelectedCurrenciesViewModel
import javax.inject.Inject

class SelectedCurrenciesViewModelFactory @Inject constructor(val getSelectedCurrenciesUseCase: GetSelectedCurrenciesUseCase,
                                         val loadSelectedCurrenciesUseCase: LoadSelectedCurrenciesUseCase,
                                         val setUnselectedCurrenciesUseCase: SetUnselectedCurrenciesUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SelectedCurrenciesViewModel(
            getSelectedCurrenciesUseCase,
            loadSelectedCurrenciesUseCase,
            setUnselectedCurrenciesUseCase) as T
    }
}