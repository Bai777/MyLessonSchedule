package com.example.mylessonschedule.data

import com.example.mylessonschedule.domain.HomeWork
import com.example.mylessonschedule.domain.Class
import io.reactivex.rxjava3.core.Single

interface Repository {
    fun getClasses(): Single<List<Class>>
    fun getHomeWorks(): Single<List<HomeWork>>
}