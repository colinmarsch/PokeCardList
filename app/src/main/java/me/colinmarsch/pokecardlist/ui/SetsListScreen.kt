package me.colinmarsch.pokecardlist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
fun SetsListScreen(
    parentSeries: String,
    navController: NavController,
    viewModel: SeriesListViewModel = hiltViewModel(),
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = parentSeries)
            SetList(parentSeries, navController, viewModel)
        }
    }
}

@Composable
fun SetList(
    parentSeries: String,
    navController: NavController,
    viewModel: SeriesListViewModel = hiltViewModel(),
) {
    val setList by remember { viewModel.allSets }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        val filteredSets = setList.filter { it.series == parentSeries }
        val setCount = filteredSets.size

        items(setCount) { index ->
            SetCard(filteredSets[index], navController)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SetCard(
    cardSet: CardSet,
    navController: NavController,
) {
    Card(
        onClick = { navController.navigate("set_card_list_screen/${cardSet.id}") },
        backgroundColor = MaterialTheme.colors.secondary,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp),
        ) {
            AsyncImage(
                model = cardSet.images.logo,
                contentDescription = cardSet.name,
                modifier = Modifier.fillMaxHeight(0.66f),
                contentScale = ContentScale.Fit,
            )
            Text(text = cardSet.name)
        }
    }
}