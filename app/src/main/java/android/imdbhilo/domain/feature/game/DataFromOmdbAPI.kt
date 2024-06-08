package android.imdbhilo.domain.feature.game




import android.imdbhilo.BuildConfig
import android.imdbhilo.domain.feature.API.OMDBService
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



@Composable
fun getDataFromOmdbAPI(movieTitle: String): Movie? {
    val apiKey = BuildConfig.API_KEY
    var movie by remember { mutableStateOf<Movie?>(null) }
    LaunchedEffect(key1 = true) {
        try {
            val response = withContext(Dispatchers.IO) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://www.omdbapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val omdbService = retrofit.create(OMDBService::class.java)
                omdbService.getMovieDetails(movieTitle, apiKey)
            }
            movie = response
        }catch (e: Exception) {
            // Handle errors
            Log.e("APIError", "Error: ${e.message}", e)
        }
    }
    return movie
}
