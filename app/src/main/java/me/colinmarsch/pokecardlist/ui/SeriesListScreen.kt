package me.colinmarsch.pokecardlist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import io.pokemontcg.model.CardSet
import me.colinmarsch.pokecardlist.data.SeriesListViewModel

@Composable
fun SeriesListScreen(
    navController: NavController,
    viewModel: SeriesListViewModel = hiltViewModel(),
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Series")
            SeriesList(navController, viewModel)
        }
    }
}

@Composable
fun SeriesList(
    navController: NavController,
    viewModel: SeriesListViewModel = hiltViewModel(),
) {
    val seriesList by remember { viewModel.seriesList }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        val seriesCount = seriesList.size
        items(seriesCount) { index ->
            SeriesCard(cardSet = seriesList[index], navController = navController)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SeriesCard(
    cardSet: CardSet,
    navController: NavController,
) {
    Card(
        onClick = { navController.navigate("sub_set_list_screen/${cardSet.series}") },
        backgroundColor = MaterialTheme.colors.secondary,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp),
        ) {
            AsyncImage(
                model = cardSet.images.logo,
                contentDescription = cardSet.series,
                modifier = Modifier.fillMaxHeight(0.66f),
                contentScale = ContentScale.Fit,
            )
            Text(text = cardSet.series)
        }
    }
}