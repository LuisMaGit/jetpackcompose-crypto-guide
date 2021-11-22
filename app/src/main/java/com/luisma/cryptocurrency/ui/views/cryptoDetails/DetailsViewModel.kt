package com.luisma.cryptocurrency.ui.views.cryptoDetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.luisma.cryptocurrency.domain.data.models.cryptoModels.cryptoDetails.CryptoDetails
import com.luisma.cryptocurrency.domain.data.repositories.cryptoRepo.ICryptoRepo
import com.luisma.cryptocurrency.domain.data.utils.models.ResponseDomain
import com.luisma.cryptocurrency.router.Routes
import com.luisma.cryptocurrency.ui.BaseViewModel
import com.luisma.cryptocurrency.ui.SimpleViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CryptoDetailsDataState(val details: CryptoDetails)

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val cryptoRepo: ICryptoRepo,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<CryptoDetailsDataState>() {

    private lateinit var _detailsData: CryptoDetailsDataState
    fun getDetails(): CryptoDetails = _detailsData.details

    private var _cryptoId : String = ""

    init {
        _cryptoId = savedStateHandle.get<String>(Routes.CryptoDetails.CRYPTO_ID)!!
        Log.v("DETAILS", state.value.toString())
        getCryptoDetails(_cryptoId)
    }

    private fun getCryptoDetails(id: String) {
        Log.v("DETAILS", id)
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = cryptoRepo.getCryptoById(id)) {
                is ResponseDomain.Success -> {
                    _detailsData = CryptoDetailsDataState(details = response.domain!!)
                    setState(SimpleViewModelState.Iddle(_detailsData))
                }
                is ResponseDomain.Error -> {
                    Log.v("DETAILS", id)
                    setState(SimpleViewModelState.Error(response.message))
                }
            }
        }
    }

    fun tryAgain() {
        setState(SimpleViewModelState.Loading())
        getCryptoDetails(_cryptoId)
    }
}