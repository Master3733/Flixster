package com.example.flixster
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.flixster_app.R
import okhttp3.Headers
import org.json.JSONException

private const val TAG="MainActivity"
private const val NOW_PLAYING="https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
class MainActivity : AppCompatActivity() {

    private val movies = mutableListOf<Movie>()
    private lateinit var rvmovies: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rvmovies=findViewById(R.id.rvMovies)
        val movieAdapter = MovieAdapter(this, movies)
        rvmovies.adapter=movieAdapter
        rvmovies.layoutManager=LinearLayoutManager(this)

        val client = AsyncHttpClient()
        client.get(NOW_PLAYING, object:JsonHttpResponseHandler(){
            override fun onFailure(
                statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?
            ) {
                Log.e(TAG, "onFailure $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                Log.i(TAG, "onSucess $json")
                try {
                    val movieJsonArray = json.jsonObject.getJSONArray("results")
                    movies.addAll(Movie.fromJsonArray(movieJsonArray))
                    movieAdapter.notifyDataSetChanged()
                    Log.i(TAG, "Movie List $movies")
                }
                catch (e:JSONException){
                    Log.e(TAG, "Encountered Exception: $e")
                }

            }

        })
    }
}