package me.colinmarsch.pokecardlist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
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
import me.colinmarsch.pokecardlist.data.CardListViewModel
import me.colinmarsch.pokecardlist.db.Card

@Composable fun CardsListScreen(
  parentSet: String,
  navController: NavController,
  viewModel: CardListViewModel = hiltViewModel(),
) {
  val cardList by remember { viewModel.cardsInSet(parentSet) }

  Surface(
    color = MaterialTheme.colors.background,
    modifier = Modifier.fillMaxSize(),
  ) {
    if (cardList.isEmpty()) {
      Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
      }
    } else {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
      ) {
        Text(text = parentSet)
        CardList(parentSet, navController, cardList)
      }
    }
  }
}

@Composable fun CardList(
  parentSet: String,
  navController: NavController,
  cardList: List<Card>,
) {
  LazyVerticalGrid(
    columns = GridCells.Fixed(2),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
    horizontalArrangement = Arrangement.spacedBy(16.dp),
  ) {
    val filteredCards = cardList.filter { it.parentSetId == parentSet }
    val setCount = filteredCards.size

    items(setCount) { index ->
      CardCard(filteredCards[index], navController)
    }
  }
}

@OptIn(ExperimentalMaterialApi::class) @Composable fun CardCard(
  card: Card,
  navController: NavController,
) {
  Card(
    onClick = {
      // TODO handle navigation here
    },
    backgroundColor = MaterialTheme.colors.secondary,
    modifier = Modifier
      .fillMaxWidth()
      .wrapContentHeight(),
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier.padding(16.dp),
    ) {
      AsyncImage(
        model = card.smallImageUrl,
        contentDescription = card.name,
        modifier = Modifier.fillMaxHeight(0.66f),
        contentScale = ContentScale.Fit,
      )
      Text(text = card.name ?: "")
    }
  }
}