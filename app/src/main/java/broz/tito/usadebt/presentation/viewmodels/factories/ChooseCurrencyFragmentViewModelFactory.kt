package broz.tito.usadebt.presentation.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import broz.tito.usadebt.domain.currencydomain.currency_v2.local.SetSelectedCurrenciesUseCase
import broz.tito.usadebt.domain.currencydomain.currency_v2.local.UnselectedCurrenciesUseCase
import broz.tito.usadebt.presentation.viewmodels.ChooseCurrencyFragmentViewModel
import javax.inject.Inject

class ChooseCurrencyFragmentViewModelFactory
    @Inject constructor(val unselectedCurrenciesUseCase: UnselectedCurrenciesUseCase,
     val setSelectedCurrenciesUseCase: SetSelectedCurrenciesUseCase): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChooseCurrencyFragmentViewModel(
            unselectedCurrenciesUseCase, setSelectedCurrenciesUseCase
        ) as T
    }
}