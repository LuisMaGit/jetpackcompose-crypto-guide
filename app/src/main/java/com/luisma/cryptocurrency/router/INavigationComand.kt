package com.luisma.cryptocurrency.router

import androidx.navigation.NamedNavArgument


interface INavigationCommand {
    val route : String
    val arguments : List<NamedNavArgument>
}