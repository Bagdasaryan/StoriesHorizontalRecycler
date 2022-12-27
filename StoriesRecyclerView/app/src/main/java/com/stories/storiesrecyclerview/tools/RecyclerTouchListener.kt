package com.stories.storiesrecyclerview.tools

import android.view.MotionEvent
import android.view.View

class RecyclerTouchListener : View.OnTouchListener {
    private val RESIZE_COEFFICIENT = 0.90f
    private val ANIMATION_DURATION = 300L

    private var objectHeight = 0f
    private var minSize = 0f

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
                animation(p0, p0?.height?.toFloat()?:0f, minSize, ANIMATION_DURATION, animCallback = object:
                    AnimationCallback {
                    override fun doOnAnimationEnd() {
                        animation(p0, p0?.height?.toFloat()?:0f, objectHeight, ANIMATION_DURATION)
                    }
                })
            }
        }
        return false
    }
}
