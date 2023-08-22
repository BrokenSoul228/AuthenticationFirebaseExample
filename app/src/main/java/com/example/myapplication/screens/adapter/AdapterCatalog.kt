package com.example.myapplication.screens.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.screens.firstpage.MovieCatalog

class AdapterCatalog(var context: Context, var listMovie: List<MovieCatalog>) :
    RecyclerView.Adapter<AdapterCatalog.MovieViewHolder>() {

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    class MovieViewHolder(itemView: View, listener : AdapterCatalog.onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val icon : ImageView = itemView.findViewById(R.id.iconMovie)
        val nameMovie : TextView = itemView.findViewById(R.id.nameMovie)
        val duration : TextView = itemView.findViewById(R.id.nameDuration)
        val genre : TextView = itemView.findViewById(R.id.nameGenre)
        val description : TextView = itemView.findViewById(R.id.descriptionMovie)
        val backgroundMode : ImageView? = itemView.findViewById(R.id.backgroundMode)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    fun setFilteredList(listMovie: List<MovieCatalog>){
        this.listMovie = listMovie
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view , parent , false)
        return MovieViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieData = listMovie[position]
//        val bitmap = BitmapFactory.decodeResource(context.resources, movieData.intro)
//        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 216, 240, false)
        holder.icon.setImageResource(movieData.intro)
        holder.nameMovie.text = movieData.name
        holder.duration.text = movieData.duration

        val genreText = movieData.genre.joinToString(", ")
        holder.genre.text = genreText
        holder.description.text = movieData.descriptionMovie
        holder.backgroundMode?.setImageDrawable(null)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }
}
