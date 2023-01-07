package me.colinmarsch.pokecardlist.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CardDao {
    @Query("SELECT * FROM card")
    fun getAll(): List<Card>

    @Query("SELECT * FROM card WHERE parentSetId = :parentSetId")
    fun getAllInSet(parentSetId: String): List<Card>

    @Insert
    fun insertAll(vararg cards: Card)

    @Delete
    fun delete(card: Card)
}