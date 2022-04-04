package com.smin.dev_matching_2021.activity.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smin.dev_matching_2021.models.Detail
import com.smin.dev_matching_2021.models.Result
import com.smin.dev_matching_2021.repository.Repository
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository) : ViewModel() {

    var lecture = MutableLiveData<Detail>()
    var refresh = MutableLiveData<Boolean>()
    private val scope = MainScope()

    fun detail(courseId: String) = scope.launch {
        refresh.postValue(true)
        repository.getDetail(courseId).let {
            lecture.postValue(it)
            refresh.postValue(false)
        }
    }
}

class DetailViewModelFactory(private val repository: Repository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        throw IllegalAccessException("Unkown Viewmodel Class")
    }
}