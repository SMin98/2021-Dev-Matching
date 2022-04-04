package com.smin.dev_matching_2021.activity.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smin.dev_matching_2021.models.Lecture
import com.smin.dev_matching_2021.repository.Repository
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*

class ListViewModel(private val repository: Repository) : ViewModel() {

    var lecture = MutableLiveData<Lecture>()
    var refresh = MutableLiveData<Boolean>()
    private val scope = MainScope()

    fun list() = scope.launch {
        refresh.postValue(true)
        repository.getList().let {
            lecture.postValue(it)
            refresh.postValue(false)
        }
    }

    fun next() = scope.launch {
        refresh.postValue(true)
        val currentLecture = lecture.value ?: return@launch
        repository.getNextList(currentLecture).let {
            val currentLectures = currentLecture.results
            val mergedLectures = currentLectures.toMutableList()
                .apply { it?.let { addAll(it.results) } }
            it?.results = mergedLectures
            lecture.postValue(it)
            refresh.postValue(false)
        }
    }

}

class ListViewModelFactory(private val repository: Repository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(repository) as T
        }
        throw IllegalAccessException("Unkown Viewmodel Class")
    }
}