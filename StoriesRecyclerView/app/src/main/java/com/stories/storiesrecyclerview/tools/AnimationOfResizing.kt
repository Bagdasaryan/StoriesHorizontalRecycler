package com.stories.storiesrecyclerview.tools

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.animation.doOnEnd

fun animation(p0: View?, from: Float, to: Float, duration: Long, animCallback: AnimationCallback? = null) {
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
