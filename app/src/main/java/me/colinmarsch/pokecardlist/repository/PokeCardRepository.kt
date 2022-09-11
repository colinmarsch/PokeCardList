package me.colinmarsch.pokecardlist.repository

import dagger.hilt.android.scopes.ActivityScoped
import io.pokemontcg.Pokemon
import io.pokemontcg.model.Card
import io.pokemontcg.model.CardSet

@ActivityScoped
class PokeCardRepository {
   private val pokemon = Pokemon()

   fun allSeries() : List<String> {
      return pokemon.set().all().map { it.series }
   }

   fun allSetsInSeries(series: String) : List<CardSet> {
      return pokemon.set().where { this.series = series }.all()
   }

   fun allCardsInSet(setName: String): List<Card> {
      return pokemon.card().where { this.set = setName }.all()
   }

   fun cardById(cardId: String) : Card {
      return pokemon.card().where { this.id = cardId }.all()[0]
   }
}