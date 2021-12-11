package com.luisma.cryptocurrency.ui.views.themeWrapper

import androidx.lifecycle.ViewModel
import com.luisma.cryptocurrency.domain.app.repositories.AppThemes
import com.luisma.cryptocurrency.domain.app.repositories.ThemeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ThemeWrapperViewModel @Inject constructor(
    private val themeRepo: ThemeRepo,
) : ViewModel() {
    fun isDarkTheme() = themeRepo.isDarkMode
}