package com.evgeniykim.poststest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.evgeniykim.poststest.Model
import com.evgeniykim.poststest.R
import com.squareup.picasso.Picasso

class Adapter (private val context: Context, private val postsList: MutableList<Model>):
RecyclerView.Adapter<Adapter.MyViewHolder>(){

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView.findViewById(R.id.image1)
        val title: TextView = itemView.findViewById(R.id.title)
        val body: TextView = itemView.findViewById(R.id.body)

        fun bind(listItem: Model) {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)



        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: Adapter.MyViewHolder, position: Int) {


        val listItem = postsList[position]
        holder.bind(listItem)

        Picasso.get().load("https://n1s2.starhit.ru/6a/46/ae/6a46aeed947a183d67d1bc48211151bf/480x496_0_2bbde84177c9ff1c2299a26a0f69f69c@480x496_0xac120003_4430520541578509619.jpg").into(holder.image)
        holder.title.text = listItem.title
        holder.body.text = listItem.body

    }

    override fun getItemCount() = postsList.size


}