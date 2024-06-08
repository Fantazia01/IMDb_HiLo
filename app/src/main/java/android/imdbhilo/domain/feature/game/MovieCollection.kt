package android.imdbhilo.domain.feature.game

import kotlin.random.Random

class MovieCollection {
    private val movieTitles: List<String> = listOf(
        "The Shawshank Redemption",
        "The Godfather",
        "The Dark Knight",
        "Schindler's List",
        "The Lord of the Rings: The Return of the King",
        "Pulp Fiction",
        "The Lord of the Rings: The Fellowship of the Ring",
        "Forrest Gump",
        "Fight Club",
        "Inception",
        "The Matrix",
        "Goodfellas",
        "City of God",
        "The Silence of the Lambs",
        "Star Wars: Episode V - The Empire Strikes Back"
    )

    fun getRandomMovieTitle(): String {
        return movieTitles.random()
    }
}

fun main() {
    val movies = MovieCollection()
    println("Random movie title: ${movies.getRandomMovieTitle()}")
}
