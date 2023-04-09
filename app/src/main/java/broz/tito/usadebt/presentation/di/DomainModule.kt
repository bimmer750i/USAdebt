package broz.tito.usadebt.presentation.di

import broz.tito.usadebt.R
import broz.tito.usadebt.domain.currencydomain.currency_v2.local.provideCurrenciesHashMap
import broz.tito.usadebt.presentation.MapToStringResources
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideCurrencyHashMap(): HashMap<String,Int> {
        return provideCurrenciesHashMap()
    }

    @Provides
    fun provideMapToStringResources(map: HashMap<String,Int>): MapToStringResources {
        return MapToStringResources(map)
    }
}