package com.smin.dev_matching_2021.models


import com.google.gson.annotations.SerializedName

data class MediaX(
    @SerializedName("course_image")
    val courseImage: CourseImageX,
    @SerializedName("course_video")
    val courseVideo: CourseVideoX,
    val image: ImageX
)