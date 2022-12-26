package com.stories.storiesrecyclerview.tools

import RecyclerTouchListener
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.stories.storiesrecyclerview.R
import com.stories.storiesrecyclerview.StoryModel

class StoriesAdapter(
    private val activityContext: Context,
    private val dataSet: List<StoryModel>,
) : RecyclerView.Adapter<StoriesAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemName: AppCompatTextView = view.findViewById(R.id.name)
        val itemIcon: AppCompatImageView = view.findViewById(R.id.icon)
        val cardView: CardView = view.findViewById(R.id.card_view)
        val notViewedStoryBackground: ImageView = view.findViewById(R.id.not_viewed_story_background)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stories, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text = dataSet[position].title
        holder.itemIcon.background = dataSet[position].icon

        if(!dataSet[position].isViewed) {
            holder.notViewedStoryBackground.visibility = View.VISIBLE
        }

        holder.cardView.setOnClickListener {
            dataSet[position].callback.doOnRecyclerItemClicked(dataSet[position].title)
        }

        // Add animation on screen touch and detouch
        holder.cardView.setOnTouchListener(RecyclerTouchListener())
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}

interface ClickCallback {
    fun doOnRecyclerItemClicked(itemName: String)
}
