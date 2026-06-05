package com.example.videofeed.ui.feed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
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

        // EKSEKUSI IMMERSIVE MODE (FULLSCREEN SEJATI)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        viewPager = findViewById(R.id.viewPager)
        playerManager = VideoPlayerManager(this)
        viewModel = FeedViewModel(AppModule.videoRepository)

        viewModel.videos.observe(this, Observer { videos ->
            val adapter = VideoPagerAdapter(videos, playerManager)
            viewPager.adapter = adapter
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        playerManager.releasePlayer()
    }
}
