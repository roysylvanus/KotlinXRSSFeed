package com.malikali.kotlinrssfeeed.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.malikali.kotlinrssfeeed.R
import com.malikali.kotlinrssfeeed.appInterface.ItemClickListener

class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener,View.OnLongClickListener {

    var tvTitle: TextView
    var tvPubDate:TextView
    var tvContent:TextView

    private var itemClickListener: ItemClickListener?=null


    init {
        tvTitle = itemView.findViewById(R.id.tvTitle) as TextView
        tvPubDate = itemView.findViewById(R.id.tvPubDate) as TextView
        tvContent = itemView.findViewById(R.id.tvContent) as TextView
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = this.itemClickListener
    }
    override fun onClick(v: View?) {
        itemClickListener!!.onClick(v,adapterPosition,false)

    }

    override fun onLongClick(v: View?): Boolean {
        itemClickListener!!.onClick(v,adapterPosition,true)
        return true
    }
}