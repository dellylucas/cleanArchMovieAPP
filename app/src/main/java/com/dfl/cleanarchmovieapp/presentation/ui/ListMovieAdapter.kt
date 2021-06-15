package com.dfl.cleanarchmovieapp.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dfl.cleanarchmovieapp.databinding.AdapterListMovieBinding
import com.dfl.cleanarchmovieapp.domain.model.Movie
import com.dfl.cleanarchmovieapp.utils.Constants.BASE_URL_MOVIES_DB_IMAGE
import com.dfl.cleanarchmovieapp.utils.loadUrl

class ListMovieAdapter :
    ListAdapter<Movie, ListMovieAdapter.ListViewHolder>(DifUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemBinding =
            AdapterListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val paymentBean = getItem(position)
        holder.bind(paymentBean)
    }

    inner class ListViewHolder(private val view: AdapterListMovieBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bind(movie: Movie) {
            view.movieTextView.text = movie.name
            movie.posterPath?.let { view.imageButton.loadUrl(BASE_URL_MOVIES_DB_IMAGE + it) }
        }

    }
}

private class DifUtil : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem == newItem

}