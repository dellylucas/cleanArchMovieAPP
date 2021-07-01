package com.dfl.cleanarchmovieapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dfl.cleanarchmovieapp.databinding.AdapterListMovieBinding
import com.dfl.model.Movie

class ListMovieAdapter(val listener: (Int) -> Unit) :
    ListAdapter<Movie, ListMovieAdapter.ListViewHolder>(DifUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemBinding =
            AdapterListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
        holder.itemView.setOnClickListener { listener(movie.id) }
    }

    inner class ListViewHolder(private val view: AdapterListMovieBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bind(movie: Movie) {
            view.movie = movie
        }

    }
}

private class DifUtil : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem == newItem

}