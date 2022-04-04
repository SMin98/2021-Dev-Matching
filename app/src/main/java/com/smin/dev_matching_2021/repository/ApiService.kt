package com.smin.dev_matching_2021.repository

import com.smin.dev_matching_2021.BuildConfig
import com.smin.dev_matching_2021.models.Detail
import com.smin.dev_matching_2021.models.Lecture
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET("courseList?" + "ServiceKey=${BuildConfig.ACCESS_KEY}" + "&Mobile=1")
    suspend fun getList(): Response<Lecture>

    @GET()
    suspend fun getNextList(@Url Url: String): Response<Lecture>

    @GET("courseDetail?" + "ServiceKey=${BuildConfig.ACCESS_KEY}")
    suspend fun getDetail(@Query("CourseId") id: String): Response<Detail>
}