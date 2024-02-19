package android.imdbhilo.domain.feature.game

import android.imdbhilo.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.gson.Gson


// Assuming you have Gson added to your project dependencies

data class Movie(
    val Title: String,
    val Poster: String,
    val Ratings: List<Rating>
)

data class Rating(
    val Value: String
)

fun parseJsonToMovie(json: String): Movie? {
    return try {
        val gson = Gson()
        val movie = gson.fromJson(json, Movie::class.java)
        movie
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
@Composable
fun GameScreen(movieJSON: String) {
    val movie = parseJsonToMovie(movieJSON)
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
            Box(modifier = Modifier){
                Image(
                    painter = rememberAsyncImagePainter(R.drawable.baseline_terrain_24),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Image(
                    painter = rememberAsyncImagePainter(movie?.Poster),
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
                        text = "Title: ${movie?.Title}",
                        color = Color.White,
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                            .background(Color.Black)
                    )
                    Text(
                        text = "Rating: ${movie?.Ratings?.firstOrNull()?.Value ?: "N/A"}",
                        color = Color.White,
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                            .background(Color.Black)
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Other content here...",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
