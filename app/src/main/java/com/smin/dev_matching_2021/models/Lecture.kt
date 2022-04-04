package com.smin.dev_matching_2021.models

data class Lecture(
    val pagination: Pagination,
    var results: List<Result>
)