package android.imdbhilo.domain.feature.navigation

import android.imdbhilo.domain.feature.game.GameScreen
import android.imdbhilo.domain.feature.hall_of_fame.HallOfFameScreen
import android.imdbhilo.domain.feature.menu.MenuScreen
import android.imdbhilo.domain.feature.game.LoseScreen
import android.imdbhilo.domain.feature.game.Movie
import android.imdbhilo.domain.feature.game.WinScreen
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
) {
    var lastMovie by remember { mutableStateOf<Movie?>(null) }
    NavHost(
        navController = navController,
        startDestination = Screen.Menu.route,
    ) {
        composable(Screen.Menu.route) {
            MenuScreen (
                onGameClick = {
                    navController.navigate(Screen.Game.route)
                },
                onHallOfFameClick = {
                    navController.navigate(Screen.HallOfFame.route)
                }

                )
        }
        composable(Screen.Game.route) {
            GameScreen(
                lastMovie = lastMovie,
                onGameWin = {result ->
                    lastMovie = result
                    navController.navigate(Screen.Game.route)
                },
                onGameLose = {
                    navController.navigate(Screen.Lose.route)
                }
            )
        }
        composable(Screen.HallOfFame.route) {
            HallOfFameScreen(
            )
        }
        composable(Screen.Lose.route){
            LoseScreen()
        }
        composable(Screen.Win.route){
            WinScreen()
        }
    }
}