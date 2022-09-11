package me.colinmarsch.pokecardlist.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.pokemontcg.model.CardSet
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.colinmarsch.pokecardlist.repository.PokeCardRepository

@HiltViewModel
class SeriesListViewModel @Inject constructor(
    private val repository: PokeCardRepository,
) : ViewModel() {

    var seriesList = mutableStateOf<List<CardSet>>(emptyList())
    var allSets = mutableStateOf<List<CardSet>>(emptyList())

    init {
        loadSeriesList()
        loadSets()
    }

    fun loadSeriesList() {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                seriesList.value = repository.allSeries()
            }
        }
    }

    fun loadSets() {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                allSets.value = repository.allSets()
            }
        }
    }
}