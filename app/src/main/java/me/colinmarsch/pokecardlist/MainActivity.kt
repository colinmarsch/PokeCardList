package me.colinmarsch.pokecardlist

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "main_set_list_screen",
            ) {
                composable("main_set_list_screen") {
                    // TODO define the composable screen here
                }

                composable(route = "sub_set_list_screen", arguments = listOf(
                    navArgument("parentSetName") {
                        type = NavType.StringType
                    }
                )) {
                    val parentSetName = it.arguments?.get("parentSetName")
                    // TODO define the composable screen here
                }

                composable(route = "set_card_list_screen", arguments = listOf(
                    navArgument("parentSetName") {
                        type = NavType.StringType
                    }
                )) {
                    val parentSetName = it.arguments?.get("parentSetName")
                    // TODO define the composable screen here
                }

                composable(route = "card_detail_screen", arguments = listOf(
                    navArgument("cardName") {
                        type = NavType.StringType
                    }
                )) {
                    val cardName = it.arguments?.get("cardName")
                    // TODO define the composable screen here
                }
            }
        }
    }
}