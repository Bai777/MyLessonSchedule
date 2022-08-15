package com.example.mylessonschedule.data

class RepositoryImplFake(private val repo:FakeRepository = FakeRepository()): Repository {
    override fun getClasses(): Single<List<Class>> {
        return repo.getClasses()
    }

    override fun getHomeWorks(): Single<List<HomeWork>> {
        return repo.getHomeworks()
    }
}