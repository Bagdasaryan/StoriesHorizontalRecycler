package com.stories.storiesrecyclerview.tools

import RecyclerTouchListener
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.stories.storiesrecyclerview.R

class StoriesAdapter(
    private val activityContext: Context,
    private val dataSet: List<String>,
    private val clickCallback: ClickCallback,
) : RecyclerView.Adapter<StoriesAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemName: AppCompatTextView = view.findViewById(R.id.name)
        val cardView: CardView = view.findViewById(R.id.card_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stories, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text = dataSet[position]

        holder.cardView.setOnClickListener {
            clickCallback.doOnRecyclerItemClicked(dataSet[position])
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

interface AnimationCallback {
    fun doOnAnimationEnd()
}
