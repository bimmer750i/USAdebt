package broz.tito.usadebt.data.sharedprefs

import android.content.Context
import broz.tito.usadebt.data.remote.Model

class SharedPrefs(val context: Context) {

    val prefs = context.getSharedPreferences("theme",Context.MODE_PRIVATE)

    fun getTheme(): Int {
        return prefs.getInt("theme",0)
    }

    fun setTheme(code: Int) {
        prefs.edit()
            .putInt("theme",code)
            .apply()
    }

    companion object {
        private lateinit var prefs: SharedPrefs
        fun getInstance(context: Context): SharedPrefs {
            return if (!this::prefs.isInitialized) {
                prefs = SharedPrefs(context)
                prefs
            } else {
                prefs
            }
        }
    }

}