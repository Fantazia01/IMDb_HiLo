package android.imdbhilo.domain.feature.API

import android.imdbhilo.domain.feature.game.Movie
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDBService {
    @GET("/")
    suspend fun getMovieDetails(
        @Query("t") title: String,
        @Query("apikey") apiKey: String
    ): Movie
}

private val omdbService by lazy {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.omdbapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    retrofit.create(OMDBService::class.java)
}
