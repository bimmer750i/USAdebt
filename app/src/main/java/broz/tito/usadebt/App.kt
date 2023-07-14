package broz.tito.usadebt

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import broz.tito.usadebt.data.remote.Model
import broz.tito.usadebt.data.remote.parsers.EnglishParser
import broz.tito.usadebt.data.remote.parsers.RParser
import broz.tito.usadebt.data.themedata.ThemeRepositoryImpl
import broz.tito.usadebt.domain.themedomain.ThemeUseCase
import broz.tito.usadebt.presentation.di.AppComponent
import broz.tito.usadebt.presentation.di.AppModule
import broz.tito.usadebt.presentation.di.DaggerAppComponent
import java.util.*
import javax.inject.Inject

class App: Application() {

    @Inject
    lateinit var applicationModel: Model

    private lateinit var useCase: ThemeUseCase

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .context(this)
            .build()

        appComponent.inject(this)

        if (Locale.getDefault().language.equals("ru")) {
            applicationModel.parser = RParser()
        }
        else {
            applicationModel.parser = EnglishParser()
        }
        useCase = ThemeUseCase(ThemeRepositoryImpl(this))
        useCase.setTheme(useCase.getTheme())

    }
}