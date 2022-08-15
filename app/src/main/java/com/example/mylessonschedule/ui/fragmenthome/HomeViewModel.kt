package com.example.mylessonschedule.ui.fragmenthome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mylessonschedule.data.Repository
import com.example.mylessonschedule.data.RepositoryImplFake
import com.example.mylessonschedule.domain.ClassesState
import com.example.mylessonschedule.domain.HomeWorkState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(private val repository: Repository = RepositoryImplFake()) : ViewModel() {

    private val liveDataClasses: MutableLiveData<ClassesState> = MutableLiveData()
    private val liveDataHomework: MutableLiveData<HomeWorkState> = MutableLiveData()

    fun getClassesData(): LiveData<ClassesState> {
        return liveDataClasses
    }

    fun getHomeworkData(): LiveData<HomeWorkState> {
        return liveDataHomework
    }

    fun getClasses() = getClassesFromRepo()

    private fun getClassesFromRepo() {
        repository.getClasses()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                liveDataClasses.postValue(ClassesState.Loading)
            }
            .subscribe({
                liveDataClasses.postValue(ClassesState.Success(it))
            }, {
                liveDataClasses.postValue(ClassesState.Error(Throwable("Connection error")))
            })
    }

    fun getHomework() = getHomeworkFromRepo()

    private fun getHomeworkFromRepo() {
        repository.getHomeWorks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                liveDataHomework.postValue(HomeWorkState.Loading)
            }
            .subscribe({
                liveDataHomework.postValue(HomeWorkState.Success(it))
            }, {
                liveDataHomework.postValue(HomeWorkState.Error(Throwable("Connection error")))
            })
    }
}