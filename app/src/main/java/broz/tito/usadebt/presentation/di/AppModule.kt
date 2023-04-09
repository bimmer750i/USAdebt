package broz.tito.usadebt.presentation.di

import android.content.Context
import broz.tito.usadebt.data.currencydata.currency_v2.LocalCurrenciesRepositoryImpl
import broz.tito.usadebt.data.currencydata.currency_v2.RemoteCurrenciesRepositoryImpl
import broz.tito.usadebt.data.currentdebtdata.CurrentDebtRepositoryImpl
import broz.tito.usadebt.data.debthistorydata.DebtHistoryRepositoryImpl
import broz.tito.usadebt.domain.currencydomain.currency_v2.local.*
import broz.tito.usadebt.domain.currencydomain.currency_v2.remote.LoadSelectedCurrenciesUseCase
import broz.tito.usadebt.domain.currencydomain.currency_v2.remote.RemoteCurrenciesRepository
import broz.tito.usadebt.domain.currentdebtdomain.CurrentDebtRepository
import broz.tito.usadebt.domain.currentdebtdomain.CurrentDebtUseCase
import broz.tito.usadebt.domain.debthistorydomain.DebtHistoryRepository
import broz.tito.usadebt.domain.debthistorydomain.DebtHistoryUseCase
import broz.tito.usadebt.presentation.MapToStringResources
import broz.tito.usadebt.presentation.viewmodels.factories.ChooseCurrencyFragmentViewModelFactory
import broz.tito.usadebt.presentation.viewmodels.factories.DebtFragmentViewModelFactory
import broz.tito.usadebt.presentation.viewmodels.factories.HistoryFragmentViewModelFactory
import broz.tito.usadebt.presentation.viewmodels.factories.SelectedCurrenciesViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideLocalCurrencyRepository(): LocalCurrenciesRepository {
        return LocalCurrenciesRepositoryImpl()
    }

    @Provides
    fun provideRemoteCurrencyRepository(): RemoteCurrenciesRepository {
        return RemoteCurrenciesRepositoryImpl()
    }

    @Provides
    fun provideCurrentDebtRepository(): CurrentDebtRepository {
        return CurrentDebtRepositoryImpl()
    }

    @Provides
    fun provideDebtHistoryRepository(): DebtHistoryRepository {
        return DebtHistoryRepositoryImpl()
    }


}