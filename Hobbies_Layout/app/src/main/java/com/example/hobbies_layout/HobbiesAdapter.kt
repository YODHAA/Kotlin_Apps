package com.example.hobbies_layout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class HobbiesAdapter(val context: Context,val hobbies:List<Hobby>): RecyclerView.Adapter<HobbiesAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun setData(hobby: Hobby?,position: Int) {
            itemView.txvTitle.text=hobby!!.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
         val view=LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
         return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
                 return hobbies.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val hobby=hobbies[position]
        holder.setData(hobby,position)
    }

}