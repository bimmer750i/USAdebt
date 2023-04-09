package broz.tito.usadebt.domain.themedomain

import broz.tito.usadebt.data.sharedprefs.SharedPrefs

class ThemeUseCase(val repository: ThemeRepository) {

    fun getTheme(): Int {
        return repository.getTheme()
    }

    fun setTheme(code: Int) {
        repository.setTheme(code)
    }


}