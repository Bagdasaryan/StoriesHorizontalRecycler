package com.stories.storiesrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
            dataSet = listOf("A", "B", "C", "D", "E", "F", "G", "H"),
            clickCallback = object: ClickCallback {
                override fun doOnRecyclerItemClicked(itemName: String) {
                    Toast.makeText(this@MainActivity, itemName, Toast.LENGTH_SHORT).show()
                }
            }
        )
        storiesRecycler.layoutManager = StoriesLayoutManager(this)
    }
}
