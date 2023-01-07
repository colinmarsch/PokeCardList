package me.colinmarsch.pokecardlist.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Card(
    @PrimaryKey val id: String,
    val name: String?,
    val smallImageUrl: String?,
    val largeImageUrl: String?,
    val parentSetId: String?,
)