package com.malikali.kotlinrssfeeed.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.malikali.kotlinrssfeeed.R
import com.malikali.kotlinrssfeeed.appInterface.ItemClickListener
import com.malikali.kotlinrssfeeed.data.RSSObject

class FeedAdapter(private val mContext: Context, private val rssObject:RSSObject):RecyclerView.Adapter<FeedViewHolder>() {
    private val inflater:LayoutInflater

    init {
        inflater = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val itemView = inflater.inflate(R.layout.row_m,parent,false)
        return FeedViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.tvTitle.text = rssObject.items[position].title
        holder.tvContent.text = rssObject.items[position].content
        holder.tvPubDate.text = rssObject.items[position].pubDate

        holder.setItemClickListener(ItemClickListener{view, position, isLongClick ->

            if (!isLongClick){
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(rssObject.items[position].link))
                mContext.startActivity(browserIntent)
            }
        })
    }

    override fun getItemCount(): Int {
        return rssObject.items.size
    }
}