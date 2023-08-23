package com.example.myapplication.screens.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.screens.anime.AnimeCatalog

class AdapterAnimeCatalog(var context: Context, var listAnime: List<AnimeCatalog>) :
    RecyclerView.Adapter<AdapterAnimeCatalog.MovieViewHolder>() {

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    class MovieViewHolder(itemView: View, listener : AdapterAnimeCatalog.onItemClickListener) : RecyclerView.ViewHolder(itemView) {
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

    fun setFilteredList(listAnime: List<AnimeCatalog>){
        this.listAnime = listAnime
        notifyDataSetChanged()
    }

    fun getItemAtPosition(position: Int): AnimeCatalog{
        return listAnime[position]
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view , parent , false)
        return MovieViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieData = listAnime[position]
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
        return listAnime.size
    }
}
