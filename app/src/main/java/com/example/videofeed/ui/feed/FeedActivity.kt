package com.example.videofeed.ui.feed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.videofeed.R
import com.example.videofeed.di.AppModule
import com.example.videofeed.ui.feed.adapter.VideoPagerAdapter
import com.example.videofeed.ui.player.VideoPlayerManager

class FeedActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var playerManager: VideoPlayerManager
    private lateinit var viewModel: FeedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        viewPager = findViewById(R.id.viewPager)
        playerManager = VideoPlayerManager(this)
        
        // Manual DI inject
        viewModel = FeedViewModel(AppModule.videoRepository)

        viewModel.videos.observe(this, Observer { videos ->
            val adapter = VideoPagerAdapter(videos, playerManager)
            viewPager.adapter = adapter
        })

        // Opsional: Pause video ketika scroll sedang berlangsung
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // Adapter onViewAttachedToWindow akan meng-handle play
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        playerManager.releasePlayer()
    }
}
