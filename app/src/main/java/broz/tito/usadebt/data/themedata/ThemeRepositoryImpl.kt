package broz.tito.usadebt.data.themedata

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import broz.tito.usadebt.data.sharedprefs.SharedPrefs
import broz.tito.usadebt.domain.themedomain.ThemeRepository

class ThemeRepositoryImpl(val context: Context): ThemeRepository {

    val prefs = SharedPrefs.getInstance(context)

    override fun getTheme(): Int {
        return prefs.getTheme()
    }

    override fun setTheme(code: Int) {
        prefs.setTheme(code)
        val theme = getTheme()
        when (theme) {
            0 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
            1 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            2 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }
}