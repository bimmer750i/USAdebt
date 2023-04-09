package broz.tito.usadebt.presentation.di

import broz.tito.usadebt.data.remote.Model
import broz.tito.usadebt.presentation.screens.currency.ChooseCurrencyFragment
import broz.tito.usadebt.presentation.screens.currency.SelectedCurrenciesFragment
import broz.tito.usadebt.presentation.screens.debt.DebtFragment
import broz.tito.usadebt.presentation.screens.history.HistoryFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, DomainModule::class])
interface AppComponent {

    fun inject(chooseCurrencyFragment: ChooseCurrencyFragment)

    fun inject(selectedCurrenciesFragment: SelectedCurrenciesFragment)

    fun inject(debtFragment: DebtFragment)

    fun inject(historyFragment: HistoryFragment)


}