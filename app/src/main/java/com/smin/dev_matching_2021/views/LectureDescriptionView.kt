package com.smin.dev_matching_2021.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.smin.dev_matching_2021.R
import com.smin.dev_matching_2021.databinding.ViewLectureDescriptionBinding

class LectureDescriptionView(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {

    constructor(context: Context) : this(context, null)

    private lateinit var binding: ViewLectureDescriptionBinding

    init {
        val view =
            LayoutInflater.from(context).inflate(R.layout.view_lecture_description, this, true)
        binding = ViewLectureDescriptionBinding.bind(view)
    }

    fun setDescription(title: String, description: String) {
        binding.descriptionTitle.text = title
        binding.description.text = description
    }
}