package android.imdbhilo.domain.feature.navigation

import android.imdbhilo.domain.feature.game.GameScreen
import android.imdbhilo.domain.feature.hall_of_fame.HallOfFameScreen
import android.imdbhilo.domain.feature.menu.MenuScreen
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
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
    NavHost(
        navController = navController,
        startDestination = Screen.Menu.route
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
            GameScreen("{\"Title\":\"Starwars: Goretech\",\"Year\":\"2018\",\"Rated\":\"N/A\",\"Released\":\"07 Dec 2018\",\"Runtime\":\"90 min\",\"Genre\":\"Action, Comedy, Sci-Fi\",\"Director\":\"Germán Magariños\",\"Writer\":\"Vic Cicuta, Germán Magariños\",\"Actors\":\"Vic Cicuta, Julieta Grimaldo, Fabian Moreno\",\"Plot\":\"N/A\",\"Language\":\"Spanish\",\"Country\":\"Argentina\",\"Awards\":\"N/A\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BNTI5OTBhMGYtNTZlNS00MjMzLTk5NTEtZDZkODM5YjYzYmE5XkEyXkFqcGdeQXVyMzU0OTU0MzY@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"4.9/10\"}],\"Metascore\":\"N/A\",\"imdbRating\":\"4.9\",\"imdbVotes\":\"37\",\"imdbID\":\"tt9336300\",\"Type\":\"movie\",\"DVD\":\"N/A\",\"BoxOffice\":\"N/A\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}"
            )
        }
        composable(Screen.HallOfFame.route) {
            HallOfFameScreen(
            )
        }

    }
}