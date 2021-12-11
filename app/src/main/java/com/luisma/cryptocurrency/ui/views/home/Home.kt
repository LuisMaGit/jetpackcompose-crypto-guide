package com.luisma.cryptocurrency.ui.views.home

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.luisma.cryptocurrency.ui.SimpleViewModelState
import com.luisma.cryptocurrency.ui.components.CenterLoader
import com.luisma.cryptocurrency.ui.components.ErrorScreen
import com.luisma.cryptocurrency.ui.components.FractionallyPageWrapper
import com.luisma.cryptocurrency.ui.views.themeWrapper.ThemeWrapper
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun Home(
    model: HomeViewModel = hiltViewModel(),
) {
    when (model.state.value) {
        is SimpleViewModelState.Loading -> CenterLoader()
        is SimpleViewModelState.Error -> ErrorScreen(onTap = { model.tryAgain() })
        is SimpleViewModelState.Iddle -> HomeIddle()
    }
}

@Composable
fun HomeIddle(
    model: HomeViewModel = hiltViewModel(),
) {
    val lazyListState = rememberLazyListState()
    val coroutineScroll = rememberCoroutineScope()
    val corutineButton = rememberCoroutineScope()
    var scaleFloatingButton by remember {
        mutableStateOf(0F)
    }
    val initialScale = 0F
    val targetScale = 1F
    val scaleAnimationFloatingButton by animateFloatAsState(
        targetValue = scaleFloatingButton,
        animationSpec = tween(
            durationMillis = 200,
            easing = LinearOutSlowInEasing
        )
    )
    val isDarkMode = model.isDarkTheme().collectAsState(initial = false).value ?: false


    fun goUp() {
        coroutineScroll.launch {
            lazyListState.scrollToItem(0)
        }
    }

    fun showFloatingButton(itemIdx: Int) {
        corutineButton.launch {
            if (itemIdx != 0 && scaleFloatingButton == initialScale) {
                scaleFloatingButton = targetScale
            }
            if (itemIdx == 0 && scaleFloatingButton == targetScale) {
                scaleFloatingButton = initialScale
            }
        }
    }

    //View
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        FractionallyPageWrapper {
            //Body
            HomeBody(
                search = model.searchValue.value,
                setSearch = {
                    model.setSearch(it)
                },
                lastUpdate = model.getLastUpdate(),
                onTapRefresh = {
                    model.onTapRefresh()
                },
                stateList = lazyListState,
                showFloatingButton = {
                    showFloatingButton(it)
                },
                cryptos = model.getCryptos(),
                isDarkMode = isDarkMode,
                setDarkMode = {
                    model.setDarkMode(it)
                },
                goToDetails = {
                    model.goToDetails(it)
                }
            )
            //Floating Action Button
            HomeFloatingButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(12.dp),
                scale = scaleAnimationFloatingButton,
                onClick = ::goUp
            )
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    ThemeWrapper {
        HomeIddle()
    }
}


