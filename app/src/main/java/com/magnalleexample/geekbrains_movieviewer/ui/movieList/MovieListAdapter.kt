package com.magnalleexample.geekbrains_movieviewer.ui.movieList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.magnalleexample.geekbrains_movieviewer.R
import com.magnalleexample.geekbrains_movieviewer.databinding.ListItemMovieViewBinding
import com.magnalleexample.geekbrains_movieviewer.domain.entity.MovieData
import com.magnalleexample.geekbrains_movieviewer.domain.repo.Repo

class MovieListAdapter(val clickListener : MovieDataListener): ListAdapter<MovieData, RecyclerView.ViewHolder>(DiffCallback)  {
    companion object DiffCallback : DiffUtil.ItemCallback<MovieData>(){
        override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieDataViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieDataViewHolder -> {
                val item: MovieDataViewHolder = holder
                item.bind(getItem(position), clickListener)
            }
        }
    }
    class MovieDataViewHolder private constructor (val binding: ListItemMovieViewBinding): RecyclerView.ViewHolder(binding.root){
        companion object {
            fun from(parent: ViewGroup): MovieDataViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemMovieViewBinding.inflate(layoutInflater, parent, false)
                return MovieDataViewHolder(binding)
            }
        }
        fun bind(movieData: MovieData, clickListener : MovieDataListener){
            val IMAGE_URL = "https://image.tmdb.org/t/p/original${movieData.imageURL}?api_key=${Repo.API_KEY}"
            binding.movieData = movieData
            binding.movieDataCardView.setOnClickListener { clickListener.onClick(movieData) }
            binding.ratingTextView.text = movieData.rating.toString()
            binding.yearTextView.text = movieData.year.toString()
            binding.titleTextView.text = movieData.name

            Glide
                .with(binding.root)
                .load(IMAGE_URL)
                .fitCenter()
                .placeholder(CircularProgressDrawable(binding.root.context).let {
                    it.start()
                    it})
                .into(binding.posterImageView)

            binding.executePendingBindings()
        }
    }
}

class MovieDataListener(private val clickListener : (movieData: MovieData) -> Unit){
    fun onClick(movieData: MovieData) = clickListener.invoke(movieData)
}