package com.luisma.cryptocurrency.ui.views.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.luisma.cryptocurrency.R
import com.luisma.cryptocurrency.domain.data.models.cryptoModels.Crypto
import com.luisma.cryptocurrency.ui.components.ActiveSign
import com.luisma.cryptocurrency.ui.components.AppText
import com.luisma.cryptocurrency.ui.components.ReducedBy
import com.luisma.cryptocurrency.ui.theme.Constants
import com.luisma.cryptocurrency.ui.theme.White


@Composable
fun HomeBody(
    search: String,
    setSearch: (value: String) -> Unit,
    stateList: LazyListState,
    showFloatingButton: (value: Int) -> Unit,
    cryptos: List<Crypto>,
    isDarkMode: Boolean,
    changeTheme: () -> Unit,
    goToDetails: (cryptoId: String) -> Unit,
) {
    Column {
        //AppBar
        Row(
            Modifier
                .height(68.dp)
                .fillMaxWidth()
                .padding(top = 12.dp),
        ) {
            //Search Bar
            SearchTextField(
                modifier = Modifier.weight(1F),
                value = search
            ) {
                setSearch(it)
            }
            SpacerH.Medium()
            //Actions
            ThemeButton(
                isDarkMode = isDarkMode,
                changeTheme = changeTheme,
            )
        }
        //List
        LazyColumn(
            state = stateList,
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            //Listener
            showFloatingButton(stateList.firstVisibleItemIndex)
            items(
                cryptos.count()
            ) { index ->
                CryptoCard(
                    crypto = cryptos[index],
                    goToDetails = goToDetails
                )
            }
        }
    }
}


@Composable
private fun CryptoCard(
    crypto: Crypto,
    goToDetails: (cryptoId: String) -> Unit,
) {
    Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(Constants.borderRadiusDefault),
        modifier = Modifier
            .padding(vertical = 6.dp)
            .defaultMinSize(68.dp)
            .clickable {
                goToDetails(crypto.id)
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.onBackground)
                .padding(vertical = 16.dp, horizontal = 8.dp),
        ) {
            AppText.B1(
                "${crypto.rank} - ${crypto.name} (${crypto.symbol})",
                modifier = Modifier.weight(1F)
            )
            ActiveSign(crypto.isActive)
        }
    }
}

@Composable
fun HomeFloatingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    scale: Float,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .size(60.dp)
            .scale(scale),
        shape = CircleShape,
        elevation = ButtonDefaults.elevation(defaultElevation = 12.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = White
        )
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            contentDescription = "")
    }
}


@Composable
private fun SearchTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    enabled: Boolean = true,
    singleLine: Boolean = true,
    readOnly: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    acentColor: Color = MaterialTheme.colors.primary,
    onValueChange: (value: String) -> Unit,
) {

    val focusManager = LocalFocusManager.current
    fun submitSearch() {
        focusManager.clearFocus()
    }

    Row(
        modifier = modifier
            .border(
                width = 2.dp,
                brush = SolidColor(acentColor),
                shape = RoundedCornerShape(Constants.borderRadiusDefault),
            )
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BasicTextField(
            modifier = Modifier.weight(1F),
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            enabled = enabled,
            singleLine = singleLine,
            readOnly = readOnly,
            maxLines = maxLines,
            cursorBrush = SolidColor(acentColor),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    submitSearch()
                }
            ),
            textStyle = TextStyle(color = MaterialTheme.colors.secondary),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    WrapperField {
                        AppText.B1(
                            "Buscar cripto...",
                            color = MaterialTheme.colors.onSecondary
                        )
                    }
                }
                WrapperField {
                    innerTextField()
                }
            },
        )
        WrapperField(
            alignment = Alignment.CenterEnd
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                modifier = Modifier
                    .weight(.1f)
                    .size(Constants.iconSizeDefault)
                    .clickable {
                        submitSearch()
                    },
                tint = MaterialTheme.colors.primary
            )
        }
    }
}

@Composable
private fun WrapperField(
    alignment: Alignment = Alignment.CenterStart,
    content: @Composable () -> Unit,
) {
    Box(
        contentAlignment = alignment,
        modifier = Modifier.fillMaxHeight()
    ) {
        content()
    }
}

@Composable
private fun ThemeButton(
    isDarkMode: Boolean,
    changeTheme: () -> Unit,
) {
    if (isDarkMode) {
        ButtonIcon(R.drawable.ic_baseline_nights_stay_24) {
            changeTheme()
        }
    } else {
        ButtonIcon(R.drawable.ic_baseline_wb_sunny_24) {
            changeTheme()
        }
    }
}

@Composable
private fun ButtonIcon(
    @DrawableRes icon: Int,
    onTap: () -> Unit,
) {
    Icon(
        painter = painterResource(icon),
        contentDescription = "",
        tint = MaterialTheme.colors.primary,
        modifier = Modifier
            .padding(8.dp)
            .size(Constants.iconSizeDefault)
            .clickable {
                onTap()
            }
    )
}

