package com.stories.storiesrecyclerview.tools

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.stories.storiesrecyclerview.R

class StoriesAdapter(
    private val activityContext: Context,
    private val dataSet: List<String>,
    private val clickCallback: ClickCallback,
) : RecyclerView.Adapter<StoriesAdapter.ViewHolder>() {
    private val RESIZE_COEFFICIENT = 0.90f
    private val ANIMATION_DURATION = 300L

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemIcon: AppCompatImageView = view.findViewById(R.id.icon)
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
//        holder.itemIcon.background = ContextCompat.getDrawable(activityContext, androidx.appcompat.R.drawable.abc_ab_share_pack_mtrl_alpha)
        holder.itemName.text = dataSet[position]

        holder.cardView.setOnClickListener {
            clickCallback.doOnRecyclerItemClicked(dataSet[position])
        }

        // Add animation on screen touch and detouch
        var objectHeight = 0f
        var minSize = 0f
        holder.cardView.setOnTouchListener(object: View.OnTouchListener {
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                if(objectHeight == 0f) {
                    objectHeight = p0?.height?.toFloat()?:0f
                    minSize = objectHeight * RESIZE_COEFFICIENT
                }
                if(p1?.action == MotionEvent.ACTION_DOWN) {
                    animation(p0, objectHeight, minSize, ANIMATION_DURATION)
                }
                if(p1?.action == MotionEvent.ACTION_UP || p1?.action == MotionEvent.ACTION_CANCEL) {
                    if(objectHeight >= p0?.height?.toFloat()?:0f) {
                        animation(p0, p0?.height?.toFloat()?:0f, minSize, ANIMATION_DURATION, animCallback = object: AnimationCallback {
                            override fun doOnAnimationEnd() {
                                animation(p0, p0?.height?.toFloat()?:0f, objectHeight, ANIMATION_DURATION)
                            }
                        })
                    }
                }
                return false
            }
        })
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    private fun animation(p0: View?, from: Float, to: Float, duration: Long, animCallback: AnimationCallback? = null) {
        val slideAnimator = ValueAnimator
            .ofFloat(from, to)
            .setDuration(duration)
        slideAnimator.addUpdateListener { animation1 ->
            val value = animation1.animatedValue as Float

            p0?.layoutParams?.height = value.toInt()
            p0?.layoutParams?.width = value.toInt()

            p0?.requestLayout()
        }
        val animationSet = AnimatorSet()
        animationSet.cancel()
        animationSet.interpolator = AccelerateDecelerateInterpolator()
        animationSet.play(slideAnimator)
        animationSet.start()

        animationSet.doOnEnd {
            animCallback?.let {
                it.doOnAnimationEnd()
            }
        }
    }
}

interface ClickCallback {
    fun doOnRecyclerItemClicked(itemName: String)
}

interface AnimationCallback {
    fun doOnAnimationEnd()
}
