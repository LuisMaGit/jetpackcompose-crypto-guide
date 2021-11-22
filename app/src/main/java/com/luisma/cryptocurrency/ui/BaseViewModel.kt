package com.luisma.cryptocurrency.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<Data> : ViewModel() {
    private var _state = mutableStateOf<SimpleViewModelState<Data?>>(SimpleViewModelState.Loading())
    fun  setState(state: SimpleViewModelState<Data?>) {
        _state.value = state
    }

    val state: State<SimpleViewModelState<Data?>> = _state
}

sealed class SimpleViewModelState<Data>(
    val message: String? = null,
    val data: Data? = null,
) {
    class Loading<Nothing> : SimpleViewModelState<Nothing>()
    class Error<Nothing>(message: String?) : SimpleViewModelState<Nothing>(message)
    class Iddle<IddleData>(data: IddleData): SimpleViewModelState<IddleData>(data = data)
}
