package android.imdbhilo.domain.feature.game

import android.imdbhilo.R
import android.imdbhilo.domain.feature.API.OMDBService
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// Assuming you have Gson added to your project dependencies

data class Movie(
    val Title: String,
    val Poster: String,
    val Ratings: List<Rating>
)

data class Rating(
    val Value: String
)

@Composable
fun GameScreen(lastMovie: Movie?, onGameWin: (Movie?) -> Unit, onGameLose: () -> Unit) {
    var movieTitle1 = ""
    var movieTitle2 = ""
    var movie1 by remember { mutableStateOf<Movie?>(null) }
    var movie2 by remember { mutableStateOf<Movie?>(null) }

    if(lastMovie != null)
        movieTitle1 = lastMovie.Title
    else
        movieTitle1 = MovieCollection().getRandomMovieTitle()
    movieTitle2 = MovieCollection().getRandomMovieTitle()
    while(movieTitle1 == movieTitle2)
        movieTitle2 = MovieCollection().getRandomMovieTitle()
    movie1= getDataFromOmdbAPI(movieTitle1)
    movie2 = getDataFromOmdbAPI(movieTitle2)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MovieUpRow(movie1, movie2)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MovieDownRow(movie1, movie2, onGameWin, onGameLose)
        }
    }
}

@Composable
fun MovieUpRow(movie1: Movie?, movie2: Movie?) {
    Box(modifier = Modifier) {
        Image(
            painter = rememberAsyncImagePainter(movie1?.Poster ?: R.drawable.baseline_terrain_24),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomStart)
        ) {
            Text(
                text = "Title: ${movie1?.Title ?: "N/A"}",
                color = Color.White,
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .background(Color.Black)
            )
            Text(
                text = "Rating: ${movie1?.Ratings?.firstOrNull()?.Value ?: "N/A"}",
                color = Color.White,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .background(Color.Black)
            )
        }
    }
}

@Composable
fun MovieDownRow(upsideMovie: Movie?, downsideMovie: Movie?, onGameWin: (Movie?) -> Unit, onGameLose: () -> Unit) {
    Box(modifier = Modifier) {
        Image(
            painter = rememberAsyncImagePainter(downsideMovie?.Poster ?: R.drawable.baseline_terrain_24),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier.width(90.dp),
                onClick = {
                    if (upsideMovie!!.Ratings[0].Value < downsideMovie!!.Ratings[0].Value) {
                        onGameWin(downsideMovie)
                    } else {
                        onGameLose()
                    }
                }
            ) {
                Text(text="Higher")
            }
            Button(
                modifier = Modifier.width(90.dp),
                onClick = {
                    if (upsideMovie!!.Ratings[0].Value > downsideMovie!!.Ratings[0].Value) {
                        onGameWin(downsideMovie)
                    } else {
                        onGameLose()
                    }
                }
            ) {
                Text(text="Lower")
            }
        }
        Column(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomStart)
        ) {
            Text(
                text = "Title: ${downsideMovie?.Title ?: "N/A"}",
                color = Color.White,
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .background(Color.Black)
            )
        }
    }
}
