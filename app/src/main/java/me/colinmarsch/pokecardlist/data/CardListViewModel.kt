package me.colinmarsch.pokecardlist.data

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
    var allCards = mutableStateOf<List<Card>>(emptyList())

    init {
        loadCards()
    }

    fun loadCards() {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                allCards.value = repository.allCards()
            }
        }
    }
}