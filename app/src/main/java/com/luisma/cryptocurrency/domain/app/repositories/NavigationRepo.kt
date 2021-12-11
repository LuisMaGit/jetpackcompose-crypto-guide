package com.luisma.cryptocurrency.domain.app.repositories

import com.luisma.cryptocurrency.router.INavigationCommand
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationRepo @Inject constructor() {

    private val _commands = MutableSharedFlow<INavigationCommand>()
    var commands = _commands.asSharedFlow()

    suspend fun goTo(
        directions : INavigationCommand
    ){
        _commands.emit(directions)
    }
}