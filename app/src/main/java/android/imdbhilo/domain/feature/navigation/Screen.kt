package android.imdbhilo.domain.feature.navigation

sealed class Screen(val route: String) {
    object Menu: Screen("menu")
    object Game: Screen("game")
    object HallOfFame: Screen("hall_of_fame")

}