package com.example.homepagefeedchallenge.view.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homepagefeedchallenge.R
import com.example.homepagefeedchallenge.data.model.HomePageFeed
import kotlinx.android.synthetic.main.feed_item_layout.view.*
import java.net.URL

class HomePageFeedAdapter(
    private var homePageFeeds: MutableList<HomePageFeed>
) :
    RecyclerView.Adapter<HomePageFeedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.feed_item_layout, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(homePageFeeds[position])
    }

    override fun getItemCount(): Int {
        return homePageFeeds.size
    }

    fun updateImages(homePageFeeds: List<HomePageFeed>) {
        this.homePageFeeds = homePageFeeds as MutableList<HomePageFeed>
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(homePageFeed: HomePageFeed) {

            when (homePageFeed.cardType) {
                "text" -> {
                    itemView.background_image.visibility = View.GONE
                    itemView.title.apply {
                        text = homePageFeed.card.value
                        setTextColor(homePageFeed.card.attributes.textColor.toColorInt())
                        textSize = homePageFeed.card.attributes.font.size.toFloat()
                    }

                }
                "title_description" -> {
                    itemView.background_image.visibility = View.GONE

                    itemView.title.apply {
                        text = homePageFeed.card.title.value
                        setTextColor(homePageFeed.card.title.attributes.textColor.toColorInt())
                        textSize = homePageFeed.card.title.attributes.font.size.toFloat()
                    }

                    itemView.description.apply {
                        text = homePageFeed.card.description.value
                        setTextColor(homePageFeed.card.description.attributes.textColor.toColorInt())
                        textSize = homePageFeed.card.description.attributes.font.size.toFloat()
                    }
                }

                "image_title_description" -> {

                    itemView.background_image.visibility = View.VISIBLE
                    itemView.background_image.layoutParams.width = homePageFeed.card.image.size.width
                    itemView.background_image.layoutParams.height = homePageFeed.card.image.size.height

                    Glide.with(itemView.context)
                        .load(homePageFeed.card.image.url)
                        .into(itemView.background_image)

                    itemView.title.apply {
                        text = homePageFeed.card.title.value
                        setTextColor(homePageFeed.card.title.attributes.textColor.toColorInt())
                        textSize = homePageFeed.card.title.attributes.font.size.toFloat()
                    }

                    itemView.description.apply {
                        text = homePageFeed.card.description.value
                        setTextColor(homePageFeed.card.description.attributes.textColor.toColorInt())
                        textSize = homePageFeed.card.description.attributes.font.size.toFloat()
                    }
                }
            }
        }

    }
}