package com.example.videofeed.data.datasource

import com.example.videofeed.data.model.VideoItem
import org.jsoup.Jsoup
import java.util.regex.Pattern

class VideoRemoteDataSource {
    
    // 1. MASUKKAN LINK WEBSITE SPESIFIK ABANG DI SINI
    private val targetWebsiteUrl = "https://stripchat.com/meiguigongzhu520"

    fun fetchVideos(): List<VideoItem> {
        val videoList = mutableListOf<VideoItem>()
        val foundUrls = mutableSetOf<String>() // Menggunakan Set agar link tidak duplikat
        var idCounter = 1

        try {
            // 2. Menyamar sebagai Browser PC standar untuk melewati pemblokiran dasar (Scraping)
            val doc = Jsoup.connect(targetWebsiteUrl)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                .timeout(10000) // Timeout 10 detik
                .get()
            
            // Ambil seluruh teks HTML mentahnya
            val htmlString = doc.html()

            // 3. METODE REGEX: Berburu link yang disembunyikan di dalam JavaScript / JSON internal halaman
            // Mencari teks yang diawali http/https dan diakhiri mp4, m3u8, atau ts
            val regex = "(https?://[^\"\\s>]+?\\.(?:mp4|m3u8|ts))"
            val pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(htmlString)

            while (matcher.find()) {
                val videoUrl = matcher.group(1)
                if (videoUrl != null && !foundUrls.contains(videoUrl)) {
                    foundUrls.add(videoUrl)
                    videoList.add(
                        VideoItem(
                            id = idCounter.toString(),
                            title = "Ekstrak Regex $idCounter",
                            videoUrl = videoUrl
                        )
                    )
                    idCounter++
                }
            }

            // 4. METODE DOM PARSER: Berburu dari tag HTML standar seperti <video> atau <source>
            val mediaElements = doc.select("video, source, a")
            for (element in mediaElements) {
                var src = element.attr("src")
                if (src.isEmpty()) src = element.attr("href") // Siapa tahu linknya ada di tag <a>

                // Pastikan link valid, formatnya benar, dan belum masuk daftar
                if (src.isNotEmpty() && (src.contains(".mp4") || src.contains(".m3u8") || src.contains(".ts"))) {
                    // Jika linknya relatif (misal: /assets/video.mp4), ubah jadi link utuh
                    val absoluteUrl = if (src.startsWith("http")) src else doc.baseUri() + src
                    
                    if (!foundUrls.contains(absoluteUrl)) {
                        foundUrls.add(absoluteUrl)
                        videoList.add(
                            VideoItem(
                                id = idCounter.toString(),
                                title = "Ekstrak Tag $idCounter",
                                videoUrl = absoluteUrl
                            )
                        )
                        idCounter++
                    }
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
            // Tangkap error jika internet putus atau website menolak koneksi
        }
        
        return videoList
    }
}
