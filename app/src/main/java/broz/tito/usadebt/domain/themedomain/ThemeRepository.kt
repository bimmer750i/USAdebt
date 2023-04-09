package broz.tito.usadebt.domain.themedomain

interface ThemeRepository {

    fun getTheme(): Int

    fun setTheme(code: Int)
}