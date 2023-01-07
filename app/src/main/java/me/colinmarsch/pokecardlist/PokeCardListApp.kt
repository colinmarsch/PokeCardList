package me.colinmarsch.pokecardlist

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import me.colinmarsch.pokecardlist.di.DaggerAppComponent

@HiltAndroidApp
class PokeCardListApp : Application() {
    override fun onCreate() {
        DaggerAppComponent.builder().application(this).build()
        super.onCreate()
    }
}