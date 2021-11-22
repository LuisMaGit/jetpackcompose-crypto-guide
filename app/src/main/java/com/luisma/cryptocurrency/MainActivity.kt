package com.luisma.cryptocurrency

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.luisma.cryptocurrency.domain.app.repositories.NavigationService
import com.luisma.cryptocurrency.router.Router
import com.luisma.cryptocurrency.ui.views.themeWrapper.ThemeWrapper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationService : NavigationService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThemeWrapper {
                Router(navigationService)
            }
        }
    }
}