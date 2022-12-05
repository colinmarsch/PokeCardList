package me.colinmarsch.pokecardlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import me.colinmarsch.pokecardlist.ui.CardsListScreen
import me.colinmarsch.pokecardlist.ui.SeriesListScreen
import me.colinmarsch.pokecardlist.ui.SetsListScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "main_set_list_screen",
            ) {
                composable("main_set_list_screen") {
                    SeriesListScreen(navController = navController)
                }

                composable(route = "sub_set_list_screen/{parentSetName}", arguments = listOf(
                    navArgument("parentSetName") {
                        type = NavType.StringType
                    }
                )) {
                    val parentSetName = it.arguments?.getString("parentSetName")!!
                    SetsListScreen(parentSeries = parentSetName, navController = navController)
                }

                composable(route = "set_card_list_screen/{parentSetId}", arguments = listOf(
                    navArgument("parentSetId") {
                        type = NavType.StringType
                    }
                )) {
                    val parentSetId = it.arguments?.getString("parentSetId")!!
                    CardsListScreen(parentSet = parentSetId, navController = navController)
                }

                composable(route = "card_detail_screen/{cardName}", arguments = listOf(
                    navArgument("cardName") {
                        type = NavType.StringType
                    }
                )) {
                    val cardName = it.arguments?.getString("cardName")
                    // TODO define the composable screen here
                }
            }
        }
    }
}