package com.smin.dev_matching_2021.repository

import com.smin.dev_matching_2021.models.Detail
import com.smin.dev_matching_2021.models.Lecture
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object Repository {

    private val ApiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(Url.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    suspend fun getList(): Lecture? =
        ApiService.getList().body()

    suspend fun getNextList(lecture: Lecture): Lecture? =
        ApiService.getNextList(lecture.pagination.next).body()

    suspend fun getDetail(id: String): Detail? =
        ApiService.getDetail(id).body()
}