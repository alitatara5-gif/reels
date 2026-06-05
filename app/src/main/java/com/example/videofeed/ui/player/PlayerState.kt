package com.example.videofeed.ui.player

sealed class PlayerState {
    object Playing : PlayerState()
    object Paused : PlayerState()
    object Buffering : PlayerState()
    data class Error(val message: String) : PlayerState()
}
