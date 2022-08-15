package com.example.mylessonschedule.ui.fragmentclasses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mylessonschedule.data.Repository
import com.example.mylessonschedule.data.RepositoryImplFake
import com.example.mylessonschedule.domain.ClassesState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ClassesViewModel(private val repository: Repository = RepositoryImplFake()) : ViewModel() {

    private val liveDataClasses: MutableLiveData<ClassesState> = MutableLiveData()

    fun getClassesData(): LiveData<ClassesState> {
        return liveDataClasses
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
}