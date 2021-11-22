package com.luisma.cryptocurrency.ui.views.themeWrapper

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisma.cryptocurrency.domain.app.repositories.AppThemes
import com.luisma.cryptocurrency.domain.app.repositories.ThemeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeWrapperViewModel @Inject constructor(
    private val themeRepo: ThemeRepo,
) : ViewModel() {

    init {
        subscribeThemeListener()
    }

    private val _darkTheme: MutableState<Boolean> =
        mutableStateOf(themeRepo.theme() == AppThemes.Dark)
    val darkTheme: State<Boolean> = _darkTheme

    private fun subscribeThemeListener(){
        viewModelScope.launch {
            themeRepo.appThemeFlow.collectLatest {
                _darkTheme.value = it == AppThemes.Dark
            }
        }
    }
}