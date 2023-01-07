package me.colinmarsch.pokecardlist.repository

import me.colinmarsch.pokecardlist.db.Card as CardModel
import dagger.hilt.android.scopes.ActivityScoped
import io.pokemontcg.Pokemon
import io.pokemontcg.model.Card
import io.pokemontcg.model.CardSet
import javax.inject.Inject
import me.colinmarsch.pokecardlist.BuildConfig
import me.colinmarsch.pokecardlist.db.AppDatabase

@ActivityScoped
class PokeCardRepository @Inject constructor(
    db: AppDatabase
) {

    private val pokemon = Pokemon(BuildConfig.API_KEY)
    private val cardDao = db.cardDao()

    suspend fun allSets(): List<CardSet> {
        return pokemon.set().all()
    }

    suspend fun allSeries(): List<CardSet> {
        return pokemon.set().all().distinctBy { it.series }
    }

    suspend fun allCards(): List<CardModel> {
        if (cardDao.getAll().isEmpty()) {
            cardDao.insertAll(*pokemon.card().all().map { it.toCardModel() }.toTypedArray())
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