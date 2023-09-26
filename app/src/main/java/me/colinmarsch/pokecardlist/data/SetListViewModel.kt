package me.colinmarsch.pokecardlist.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.pokemontcg.model.CardSet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.colinmarsch.pokecardlist.repository.PokeCardRepository
import javax.inject.Inject

@HiltViewModel
class SetListViewModel @Inject constructor(
  private val repository: PokeCardRepository,
) : ViewModel() {

  var allSets = mutableStateOf<MutableMap<String, MutableState<List<CardSet>>>>(mutableMapOf())

  fun loadSets(seriesId: String): MutableState<List<CardSet>> {
    val state = mutableStateOf<List<CardSet>>(emptyList())
    viewModelScope.launch {
      CoroutineScope(Dispatchers.IO).launch {
        state.value = repository.allSetsInSeries(seriesId)
        allSets.value[seriesId] = state
      }
    }
    return allSets.value[seriesId] ?: state
  }
}