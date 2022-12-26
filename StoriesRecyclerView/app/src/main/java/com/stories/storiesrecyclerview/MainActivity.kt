package com.stories.storiesrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.stories.storiesrecyclerview.tools.ClickCallback
import com.stories.storiesrecyclerview.tools.StoriesAdapter
import com.stories.storiesrecyclerview.tools.StoriesLayoutManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initStoriesRecycler()
    }

    private fun initStoriesRecycler() {
        val storiesRecycler: RecyclerView = findViewById(R.id.stories_recycler)
        storiesRecycler.adapter = StoriesAdapter(
            activityContext = this,
            dataSet = listOf(
                StoryModel(
                    isViewed = false,
                    icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.test_icon),
                    title = "A",
                    callback = object: ClickCallback {
                        override fun doOnRecyclerItemClicked(itemName: String) {
                            Toast.makeText(this@MainActivity, itemName, Toast.LENGTH_SHORT).show()
                        }
                    }
                ),

                StoryModel(
                    isViewed = true,
                    icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.test_icon),
                    title = "B",
                    callback = object: ClickCallback {
                        override fun doOnRecyclerItemClicked(itemName: String) {
                            Toast.makeText(this@MainActivity, itemName, Toast.LENGTH_SHORT).show()
                        }
                    }
                ),

                StoryModel(
                    isViewed = false,
                    icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.test_icon),
                    title = "C",
                    callback = object: ClickCallback {
                        override fun doOnRecyclerItemClicked(itemName: String) {
                            Toast.makeText(this@MainActivity, itemName, Toast.LENGTH_SHORT).show()
                        }
                    }
                ),

                StoryModel(
                    isViewed = false,
                    icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.test_icon),
                    title = "D",
                    callback = object: ClickCallback {
                        override fun doOnRecyclerItemClicked(itemName: String) {
                            Toast.makeText(this@MainActivity, itemName, Toast.LENGTH_SHORT).show()
                        }
                    }
                ),

                StoryModel(
                    isViewed = true,
                    icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.test_icon),
                    title = "E",
                    callback = object: ClickCallback {
                        override fun doOnRecyclerItemClicked(itemName: String) {
                            Toast.makeText(this@MainActivity, itemName, Toast.LENGTH_SHORT).show()
                        }
                    }
                ),

                StoryModel(
                    isViewed = true,
                    icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.test_icon),
                    title = "F",
                    callback = object: ClickCallback {
                        override fun doOnRecyclerItemClicked(itemName: String) {
                            Toast.makeText(this@MainActivity, itemName, Toast.LENGTH_SHORT).show()
                        }
                    }
                ),

                StoryModel(
                    isViewed = false,
                    icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.test_icon),
                    title = "G",
                    callback = object: ClickCallback {
                        override fun doOnRecyclerItemClicked(itemName: String) {
                            Toast.makeText(this@MainActivity, itemName, Toast.LENGTH_SHORT).show()
                        }
                    }
                ),

                StoryModel(
                    isViewed = false,
                    icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.test_icon),
                    title = "H",
                    callback = object: ClickCallback {
                        override fun doOnRecyclerItemClicked(itemName: String) {
                            Toast.makeText(this@MainActivity, itemName, Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            ),
        )
        storiesRecycler.layoutManager = StoriesLayoutManager(this)
    }
}
