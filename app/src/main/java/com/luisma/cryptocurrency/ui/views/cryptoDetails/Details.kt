package com.luisma.cryptocurrency.ui.views.cryptoDetails

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.luisma.cryptocurrency.R
import com.luisma.cryptocurrency.domain.data.models.cryptoModels.cryptoDetails.Tag
import com.luisma.cryptocurrency.domain.data.models.cryptoModels.cryptoDetails.Team
import com.luisma.cryptocurrency.ui.SimpleViewModelState
import com.luisma.cryptocurrency.ui.components.*
import com.luisma.cryptocurrency.ui.views.themeWrapper.ThemeWrapper

@Composable
fun CryptoDetails(
    model: DetailsViewModel = hiltViewModel(),
) {

    when (model.state.value) {
        is SimpleViewModelState.Loading -> CenterLoader()
        is SimpleViewModelState.Error -> ErrorScreen {
            model.tryAgain()
        }
        is SimpleViewModelState.Iddle -> {
            val details = model.getDetails()
            CryptoDetailsBody(
                title = details.name,
                active = details.isActive,
                symbol = details.symbol,
                tags = details.tags,
                description = details.description,
                teams = details.team
            )
        }
    }
}

@Composable
fun CryptoDetailsBody(
    title: String,
    symbol: String,
    active: Boolean,
    description: String,
    tags: List<Tag>,
    teams: List<Team>,
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        FractionallyPageWrapper {
            LazyColumn(
                content = {
                    item {
                        SpacerV.Bigger()
                        TitleHeader(title, symbol, active)
                        SpacerV.Big()
                        AppText.B2(description)
                        SpacerV.Medium()
                        AppText.H2(stringResource(id = R.string.details_tags))
                        FlowRowTags(tags = tags)
                        SpacerV.Medium()
                        AppText.H2(stringResource(id = R.string.details_team_members))
                        SpacerV.Small()
                        teams.forEach {
                            TeamMember(
                                modifier = Modifier.padding(vertical = 8.dp),
                                team = it,
                            )
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun TeamMember(
    modifier: Modifier = Modifier,
    team: Team,
) {
    Column(
        modifier = modifier
    ) {
        AppText.B2(team.name)
        SpacerV.Small()
        AppText.Caption(team.position)
        SpacerV.Medium()
        Divider(
            color = MaterialTheme.colors.onSecondary,
            thickness = 1.dp,
        )
    }
}

@Composable
private fun TitleHeader(
    title: String,
    symbol: String,
    active: Boolean,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AppText.H1(
            "$title ($symbol)",
            modifier = Modifier.weight(1F),
        )
        ActiveSign(active = active)
    }
}

@Composable
private fun FlowRowTags(
    modifier: Modifier = Modifier,
    tags: List<Tag>,
) {
    FlowRow(
        modifier = modifier
    ) {
        tags.forEach {
            Tag(value = it.name)
        }
    }
}

@Composable
private fun Tag(value: String) {
    AppText.B2(
        value,
        color = MaterialTheme.colors.primary,
        modifier = Modifier
            .padding(end = 12.dp, top = 10.dp)
            .border(
                width = 1.5.dp,
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(24.dp))
            .padding(12.dp),
    )
}


@Preview
@Composable
fun CryptDatailsPreview() {
    ThemeWrapper {
        Box(
            contentAlignment = Alignment.TopCenter
        ) {
            FractionallyPageWrapper {
                CryptoDetailsBody(
                    title = "Bitcoin",
                    active = false,
                    symbol = "BTC",
                    tags = List(10) {
                        Tag(id = it.toString(), name = "Tag - $it")
                    },
                    description = "Description Description Description Description Description Description Description ",
                    teams = List(10) {
                        Team(
                            id = it.toString(),
                            name = "Name $it",
                            position = "Position $it"
                        )
                    }
                )
            }
        }
    }
}

