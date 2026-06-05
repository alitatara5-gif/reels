package com.example.videofeed.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.videofeed.ui.feed.FeedActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Redirect langsung ke FeedActivity
        startActivity(Intent(this, FeedActivity::class.java))
        finish()
    }
}
