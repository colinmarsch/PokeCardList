package me.colinmarsch.pokecardlist.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.colinmarsch.pokecardlist.db.Card
import me.colinmarsch.pokecardlist.repository.PokeCardRepository

@HiltViewModel
class CardListViewModel @Inject constructor(
    private val repository: PokeCardRepository,
) : ViewModel() {
    var cards = mutableStateOf<MutableMap<String, MutableState<List<Card>>>>(mutableMapOf())

    fun cardsInSet(setId: String): MutableState<List<Card>> {
        val setState = mutableStateOf<List<Card>>(emptyList())
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                setState.value = repository.allCardsInSet(setId)
                cards.value[setId] = setState
            }
        }
        return cards.value[setId] ?: setState
    }
}