package broz.tito.usadebt.presentation.di

import broz.tito.usadebt.data.remote.BaseRemoteService
import broz.tito.usadebt.data.remote.CurrencyRemoteService
import broz.tito.usadebt.data.remote.Model
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Named("base")
    fun provideBaseRemoteService(): BaseRemoteService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.fiscaldata.treasury.gov/services/api/fiscal_service/v2/accounting/od/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(BaseRemoteService::class.java)
    }

    @Provides
    @Named("currency")
    fun provideCurrencyV1RemoteService(): BaseRemoteService {
        val retrofit1 = Retrofit.Builder()
            .baseUrl("https://api.fiscaldata.treasury.gov/services/api/fiscal_service/v1/accounting/od/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit1.create(BaseRemoteService::class.java)
    }

    @Provides
    fun provideCurrencyV2RemoteService(): CurrencyRemoteService {
        val retrofit2 = Retrofit.Builder()
            .baseUrl("https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit2.create(CurrencyRemoteService::class.java)
    }

}