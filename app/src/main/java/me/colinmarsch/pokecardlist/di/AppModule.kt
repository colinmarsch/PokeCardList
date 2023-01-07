package me.colinmarsch.pokecardlist.di

import android.app.Application
import androidx.room.Room
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import me.colinmarsch.pokecardlist.db.AppDatabase
import me.colinmarsch.pokecardlist.repository.PokeCardRepository

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun bindDb(applicationContext: Application): AppDatabase = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "app-database"
    ).build()

    @Singleton
    @Provides
    fun bindPokeCardRepository(
        db: Lazy<AppDatabase>
    ): PokeCardRepository = PokeCardRepository(db.get())
}