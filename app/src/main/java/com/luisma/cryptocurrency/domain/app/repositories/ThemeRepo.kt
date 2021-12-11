package com.luisma.cryptocurrency.domain.app.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

enum class AppThemes {
    Light,
    Dark,
    System
}

@Singleton
class ThemeRepo @Inject constructor(
    private val prefRepo: PreferencesRepo,
) {

    private fun theme() : Flow<String?> = prefRepo.read(DataStoreKey.Theme)

    val isDarkMode = theme().map {
        when (it) {
            AppThemes.Light.toString() -> false
            AppThemes.Dark.toString() -> true
            else -> null
        }
    }

    suspend fun setTheme(value: AppThemes) {
        prefRepo.save(DataStoreKey.Theme, value.toString())
    }

}