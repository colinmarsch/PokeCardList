package me.colinmarsch.pokecardlist.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import me.colinmarsch.pokecardlist.repository.PokeCardRepository

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun bindPokeCardRepository(): PokeCardRepository = PokeCardRepository()
}