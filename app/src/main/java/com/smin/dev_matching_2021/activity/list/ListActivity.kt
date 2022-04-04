package com.smin.dev_matching_2021.activity.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smin.dev_matching_2021.AppApplication
import com.smin.dev_matching_2021.activity.detail.DetailActivity
import com.smin.dev_matching_2021.databinding.ActivityListBinding
import com.smin.dev_matching_2021.models.Lecture
import com.smin.dev_matching_2021.utils.toVisibility

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    private lateinit var viewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = (application as AppApplication).repository
        viewModel = ViewModelProvider(this, ListViewModelFactory(repository)).get(
            ListViewModel::class.java
        )

        val adapter = ListAdapter(ClickListener = this::startDetailActivity)
        binding.lectureList.adapter = adapter

        viewModel.lecture.observe(this) {
            adapter.updateLectures(it.results)
            binding.pullToRefresh.isRefreshing = false
        }

        binding.pullToRefresh.setOnRefreshListener {
            viewModel.list()
        }

        viewModel.refresh.observe(this) {
            binding.progressBar.visibility = it.toVisibility()
        }

        binding.lectureList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = binding.lectureList.layoutManager
                if (viewModel.refresh.value == false) {
                    val lastItem = (layoutManager as LinearLayoutManager)
                        .findLastCompletelyVisibleItemPosition()

                    if (layoutManager.itemCount <= lastItem + 5) {
                        viewModel.next()
                    }
                }
            }
        })

        viewModel.list()

    }

    private fun startDetailActivity(id: String) {
        startActivity(
            Intent(this, DetailActivity::class.java)
                .apply { putExtra(DetailActivity.INTENT_PARAM_COURSE_ID, id) }
        )
    }
}