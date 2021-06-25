package com.example.myapiapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapiapp.model.PhotoItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_photo_list.view.*

class PhotoAdapter(private val context: Context, private var photoList: List<PhotoItem>): RecyclerView.Adapter<PhotoAdapter.MyViewHolder>() {


    class MyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        var image : ImageView
        var txt_title : TextView
        var txt_id: TextView

        init {
            image = itemView.imageView
            txt_title = itemView.txt_title
            txt_id = itemView.txt_id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.layout_photo_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(photoList[position].url).into(holder.image)
        holder.txt_title.text = photoList[position].title
        holder.txt_id.text= photoList[position].id.toString()
    }

    override fun getItemCount(): Int {
        return photoList.size
    }
}