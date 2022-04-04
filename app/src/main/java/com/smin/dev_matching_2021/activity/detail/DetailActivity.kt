package com.smin.dev_matching_2021.activity.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.smin.dev_matching_2021.AppApplication
import com.smin.dev_matching_2021.activity.list.ListViewModel
import com.smin.dev_matching_2021.activity.list.ListViewModelFactory
import com.smin.dev_matching_2021.databinding.ActivityDetailBinding
import com.smin.dev_matching_2021.models.Detail
import com.smin.dev_matching_2021.models.Lecture
import com.smin.dev_matching_2021.models.Result
import com.smin.dev_matching_2021.utils.DateUtil
import com.smin.dev_matching_2021.utils.toVisibility

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = (application as AppApplication).repository
        viewModel = ViewModelProvider(this, DetailViewModelFactory(repository)).get(
            DetailViewModel::class.java
        )

        val courseId = intent.getStringExtra(INTENT_PARAM_COURSE_ID)
        if (courseId == null || courseId.isEmpty()) {
            finish()
            return
        }

        viewModel.lecture.observe(this) {
            setDetailInfo(it)
        }
        viewModel.refresh.observe(this) { visible ->
            binding.progressBar.visibility = visible.toVisibility()
        }
        binding.toolbar.setNavigationOnClickListener { finish() }

        viewModel.detail(courseId)
    }

    companion object {
        const val INTENT_PARAM_COURSE_ID = "param_course_id"
    }

    private fun setDetailInfo(detail: Detail) {
        binding.toolbar.title = detail.name
        Glide.with(binding.lectureImage.context)
            .load(detail.media.image.large)
            .into(binding.lectureImage)
        binding.lectureNumber.setDescription("• 강좌번호 :", detail.number)
        binding.lectureType.setDescription(
            "• 강좌분류 :",
            "${detail.classfyName} (${detail.middleClassfyName})"
        )
        binding.lectureOrg.setDescription("• 운영기관 :", detail.orgName)

        binding.lectureTeachers.setDescription("• 교수정보 :", detail.teachers ?: "")
        binding.lectureTeachers.visibility = (detail.teachers?.isEmpty() == false).toVisibility()

        binding.lectureDue.setDescription(
            "• 운영기간 :",
            DateUtil.dueString(detail.start, detail.end)
        )

        binding.webView.loadData(detail.overview ?: "", "text/html", "UTF-8")
        binding.webView.visibility = (detail.overview?.isEmpty() == false).toVisibility()
    }
}