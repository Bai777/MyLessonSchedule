package com.example.mylessonschedule.domain

sealed class HomeWorkState {
    data class Success(val data: List<HomeWork>) : HomeWorkState()
    class Error(val error: Throwable) : HomeWorkState()
    object Loading : HomeWorkState()
}