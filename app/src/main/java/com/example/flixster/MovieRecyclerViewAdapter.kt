package com.example.flixster

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.flixster_app.R
import org.w3c.dom.Text

class MovieAdapter(private val context: Context, private val movies: List<Movie>): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=  LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie= movies[position]
        holder.bind(movie)
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val ivPoster= itemView.findViewById<ImageView>(R.id.imageView)
        private val tvTitle= itemView.findViewById<TextView>(R.id.tvTitles)
        private val tvOverview=itemView.findViewById<TextView>(R.id.tvOverview)
        fun bind(movie:Movie){
            tvTitle.text=movie.title
            tvOverview.text=movie.overview
            Glide.with(context).load(movie.posterImageUrl).into(ivPoster)

        }
    }

}