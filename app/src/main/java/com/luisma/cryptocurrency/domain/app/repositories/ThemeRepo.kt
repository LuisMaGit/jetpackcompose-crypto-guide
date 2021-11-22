package com.luisma.cryptocurrency.domain.app.repositories

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

enum class AppThemes {
    Light,
    Dark
}

@Singleton
class ThemeRepo @Inject constructor() {

    private val _appThemeFlow = MutableSharedFlow<AppThemes>()
    var appThemeFlow = _appThemeFlow.asSharedFlow()
    private var _theme = AppThemes.Dark
    fun theme():AppThemes = _theme

    suspend fun setTheme(value: AppThemes){
        _theme = value
        _appThemeFlow.emit(_theme)
    }

}