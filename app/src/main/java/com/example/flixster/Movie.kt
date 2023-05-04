package com.example.flixster
import org.json.JSONArray

data class Movie (
    val movieId: Int,
    val posterPath: String,
    val title:String,
    val overview: String,
)
{
    val posterImageUrl="https://image.tmdb.org/t/p/w500/$posterPath"
    companion object{
        fun fromJsonArray(movieJsonArray: JSONArray): List<Movie> {
            val movies= mutableListOf<Movie>()
            for (i in 0 until movieJsonArray.length()){
                val moviejson= movieJsonArray.getJSONObject(i)
                movies.add(
                    Movie(
                        moviejson.getInt("id"),
                        moviejson.getString("poster_path"),
                        moviejson.getString("title"),
                        moviejson.getString("overview")

                    )
                )
            }
            return movies
        }

    }
}