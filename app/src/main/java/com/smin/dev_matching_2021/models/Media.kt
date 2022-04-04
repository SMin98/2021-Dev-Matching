package com.smin.dev_matching_2021.models


import com.google.gson.annotations.SerializedName

data class Media(
    @SerializedName("course_image")
    val courseImage: CourseImage,
    @SerializedName("course_video")
    val courseVideo: CourseVideo,
    val image: Image
)