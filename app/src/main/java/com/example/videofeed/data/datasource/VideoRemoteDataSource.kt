package com.example.videofeed.data.datasource

import com.example.videofeed.data.model.VideoItem
import org.jsoup.Jsoup
import java.util.regex.Pattern

class VideoRemoteDataSource {
    fun fetchVideos(targetUrl: String): List<VideoItem> {
        val videoList = mutableListOf<VideoItem>()

        // Langsung putar jika link mentah (m3u8, mp4, ts)
        if (targetUrl.contains(".m3u8") || targetUrl.contains(".mp4") || targetUrl.contains(".ts")) {
            videoList.add(VideoItem(id = "1", title = "Direct Stream", videoUrl = targetUrl))
            return videoList
        }

        // Jalankan Scraper jika link berupa HTML
        val foundUrls = mutableSetOf<String>()
        var idCounter = 1

        try {
            val doc = Jsoup.connect(targetUrl)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                .timeout(10000)
                .get()
            
            val htmlString = doc.html()
            val regex = "(https?://[^\"\\s>]+?\\.(?:mp4|m3u8|ts))"
            val pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(htmlString)

            while (matcher.find()) {
                val videoUrl = matcher.group(1)
                if (videoUrl != null && !foundUrls.contains(videoUrl)) {
                    foundUrls.add(videoUrl)
                    videoList.add(VideoItem(idCounter.toString(), "Extracted Video $idCounter", videoUrl))
                    idCounter++
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return videoList
    }
}
