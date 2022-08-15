package com.example.mylessonschedule.data

import io.reactivex.rxjava3.core.Single

interface Repository {
    fun getClasses(): Single<List<Class>>
    fun getHomeWorks(): Single<List<HomeWork>>
}