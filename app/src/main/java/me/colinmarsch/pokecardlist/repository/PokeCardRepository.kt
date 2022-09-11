package me.colinmarsch.pokecardlist.repository

import dagger.hilt.android.scopes.ActivityScoped
import io.pokemontcg.Pokemon
import io.pokemontcg.model.Card
import io.pokemontcg.model.CardSet

@ActivityScoped
class PokeCardRepository {
    private val pokemon = Pokemon()

    fun allSets(): List<CardSet> {
        return pokemon.set().all()
    }

    fun allSeries(): List<CardSet> {
        return pokemon.set().all().distinctBy { it.series }
    }

    fun allCardsInSet(setName: String): List<Card> {
        return pokemon.card().where { this.set = setName }.all()
    }

    fun cardById(cardId: String): Card {
        return pokemon.card().where { this.id = cardId }.all()[0]
    }
}