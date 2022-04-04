package com.smin.dev_matching_2021.models


import com.google.gson.annotations.SerializedName

data class Pagination(
    val count: Int,
    val next: String,
    @SerializedName("num_pages")
    val numPages: Int,
    val previous: Any?
)