package com.example.mylessonschedule.data

import com.example.mylessonschedule.domain.FakeRepository
import com.example.mylessonschedule.domain.HomeWork
import com.example.mylessonschedule.domain.Class
import io.reactivex.rxjava3.core.Single

class RepositoryImplFake(private val repo: FakeRepository = FakeRepository()) : Repository {
    override fun getClasses(): Single<List<Class>> {
        return repo.getClasses()
    }

    override fun getHomeWorks(): Single<List<HomeWork>> {
        return repo.getHomeworks()
    }
}