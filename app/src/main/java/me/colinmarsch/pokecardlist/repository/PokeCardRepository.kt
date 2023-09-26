package me.colinmarsch.pokecardlist.repository

import me.colinmarsch.pokecardlist.db.Card as CardModel
import dagger.hilt.android.scopes.ActivityScoped
import io.pokemontcg.Pokemon
import io.pokemontcg.model.Card
import io.pokemontcg.model.CardSet
import io.pokemontcg.requests.QueryBuilder
import javax.inject.Inject
import me.colinmarsch.pokecardlist.BuildConfig
import me.colinmarsch.pokecardlist.db.AppDatabase

@ActivityScoped
class PokeCardRepository @Inject constructor(
  db: AppDatabase
) {

  private val pokemon = Pokemon(BuildConfig.API_KEY)
  private val cardDao = db.cardDao()

  suspend fun  allSetsInSeries(seriesId: String): List<CardSet> {
    // TODO: store this in the db
    return pokemon.set().where(QueryBuilder(query = "series:\"$seriesId\"")).all()
  }

  suspend fun allSeries(): List<CardSet> {
    // TODO: store this in the db
    return pokemon.set().all().distinctBy { it.series }
  }

  suspend fun allCardsInSet(setId: String): List<CardModel> {
    // TODO: Refresh cards after some time period has passed
    if (cardDao.getAllInSet(setId).isEmpty()) {
      val allCardsFromAPI =
        pokemon.card().where(QueryBuilder(query = "set.id:$setId")).all().map { it.toCardModel() }
          .toTypedArray()
      cardDao.insertAll(*allCardsFromAPI)
    }
    return cardDao.getAll()
  }

  private fun Card.toCardModel(): CardModel = CardModel(
    id = id,
    name = name,
    smallImageUrl = images.small,
    largeImageUrl = images.large,
    parentSetId = set.id,
  )
}