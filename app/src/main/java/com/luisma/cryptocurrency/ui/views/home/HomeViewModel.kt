package com.luisma.cryptocurrency.ui.views.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.luisma.cryptocurrency.domain.app.repositories.AppThemes
import com.luisma.cryptocurrency.domain.app.repositories.NavigationService
import com.luisma.cryptocurrency.domain.app.repositories.ThemeRepo
import com.luisma.cryptocurrency.domain.data.models.cryptoModels.Crypto
import com.luisma.cryptocurrency.domain.data.repositories.cryptoRepo.ICryptoRepo
import com.luisma.cryptocurrency.domain.data.utils.models.ResponseDomain
import com.luisma.cryptocurrency.router.Routes
import com.luisma.cryptocurrency.ui.BaseViewModel
import com.luisma.cryptocurrency.ui.SimpleViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

data class HomeDataState(
    val cryptos: List<Crypto>,
    var filteredCryptos: List<Crypto> = listOf(),
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val cryptoRepo: ICryptoRepo,
    private val themeRepo: ThemeRepo,
    private val navigationService: NavigationService,
) : BaseViewModel<HomeDataState>() {

    init {
        fetchCryptos()
    }

    //STATES
    private lateinit var _homeDataState: HomeDataState
    fun getCryptos(): List<Crypto> {
        return if (_searchValue.value.isEmpty()) _homeDataState.cryptos else _homeDataState.filteredCryptos
    }

    private val _searchValue = mutableStateOf("")
    val searchValue: State<String> = _searchValue
    fun setSearch(search: String) {
        _searchValue.value = search
        findCrypto()
    }

    private val _isDarkMode = mutableStateOf(themeRepo.theme() == AppThemes.Dark)
    val isDarkMode: State<Boolean> = _isDarkMode


    private fun findCrypto() {
        val filtered = mutableListOf<Crypto>()
        _homeDataState.cryptos.forEach {
            if (_searchValue.value.lowercase(Locale.getDefault()) in
                it.name.lowercase(Locale.getDefault())
            ) {
                filtered.add(it)
            }
        }
        _homeDataState.filteredCryptos = filtered
    }

    private fun fetchCryptos() = viewModelScope.launch(Dispatchers.IO) {
        when (val result = cryptoRepo.getCyptos()) {
            is ResponseDomain.Success -> {
                _homeDataState = HomeDataState(cryptos = result.domain!!)
                setState(SimpleViewModelState.Iddle(_homeDataState))
            }
            is ResponseDomain.Error -> {
                setState(SimpleViewModelState.Error(result.message))
            }
        }
    }

    fun changeTheme() {
        _isDarkMode.value = !_isDarkMode.value
        if (!_isDarkMode.value) {
            viewModelScope.launch {
                themeRepo.setTheme(AppThemes.Light)
            }
            return
        }

        viewModelScope.launch {
            themeRepo.setTheme(AppThemes.Dark)
        }
    }

    fun goToDetails(cryptoId: String) {
        viewModelScope.launch {
            navigationService.goTo(
                Routes.CryptoDetails.goToCrypto(cryptoId)
            )
        }
    }

    fun tryAgain() {
        setState(SimpleViewModelState.Loading())
        fetchCryptos()
    }
}