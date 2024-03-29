package com.binar.movieapp.ui.favorite

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binar.movieapp.R
import com.binar.movieapp.data.local.FavoriteMovie
import com.binar.movieapp.databinding.ItemMovieBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private lateinit var list : List<FavoriteMovie>

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setMovieList(list: List<FavoriteMovie>){
        this.list = list
        notifyDataSetChanged()
    }

    inner class FavoriteViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteMovie: FavoriteMovie) {
            with(binding) {

                Glide.with(itemView)
                    .load("${favoriteMovie.baseUrl}${favoriteMovie.poster_path}")
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(ivMoviePoster)
                tvMovieTitle.text = favoriteMovie.original_title
                binding.root.setOnClickListener { onItemClickCallback?.onItemClick(favoriteMovie) }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        Log.e("adapter", "Masuk bind view holder")
        holder.bind(list[position])
    }

    interface OnItemClickCallback {
        fun onItemClick(favoriteMovie: FavoriteMovie)
    }
}