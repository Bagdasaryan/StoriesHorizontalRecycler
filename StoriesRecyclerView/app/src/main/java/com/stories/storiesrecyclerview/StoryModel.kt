package com.stories.storiesrecyclerview

import android.graphics.drawable.Drawable
import com.stories.storiesrecyclerview.tools.ClickCallback

data class StoryModel (
    val isViewed: Boolean = false,
    val icon: Drawable? = null,
    val title: String = "",
    val callback: ClickCallback,
)
