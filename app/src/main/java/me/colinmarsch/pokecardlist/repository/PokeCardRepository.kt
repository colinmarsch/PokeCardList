package me.colinmarsch.pokecardlist.repository

import dagger.hilt.android.scopes.ActivityScoped
import io.pokemontcg.Pokemon
import io.pokemontcg.model.Card
import io.pokemontcg.model.CardSet
import me.colinmarsch.pokecardlist.BuildConfig

@ActivityScoped
class PokeCardRepository {

    private val pokemon = Pokemon(BuildConfig.API_KEY)

    suspend fun allSets(): List<CardSet> {
        return pokemon.set().all()
    }

    suspend fun allSeries(): List<CardSet> {
        return pokemon.set().all().distinctBy { it.series }
    }

    suspend fun allCards(): List<Card> {
        return pokemon.card().all()
    }
}