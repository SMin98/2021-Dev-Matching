package com.smin.dev_matching_2021.activity.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smin.dev_matching_2021.R
import com.smin.dev_matching_2021.databinding.ViewListItemBinding
import com.smin.dev_matching_2021.models.Result
import com.smin.dev_matching_2021.utils.DateUtil

class ListAdapter(val ClickListener: (String) -> Unit) :
    RecyclerView.Adapter<LectureViewHolder>() {

    private val lectures = mutableListOf<Result>()

    fun updateLectures(lectures: List<Result>) {
        this.lectures.clear()
        this.lectures.addAll(lectures)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return lectures.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LectureViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_list_item, parent, false)
        val binding = ViewListItemBinding.bind(view)
        return LectureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LectureViewHolder, position: Int) {
        val lecture = lectures[position]
        holder.bind(lecture)
        holder.itemView.setOnClickListener {
            ClickListener(lecture.id)
        }
    }
}

class LectureViewHolder(binding: ViewListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    private val thumbnail = binding.lectureImage
    private val title = binding.lectureTitle
    private val orgName = binding.lectureFrom
    private val period = binding.lectureDuration

    fun bind(result: Result) {
        title.text = result.name
        orgName.text = result.orgName
        period.text = DateUtil.dueString(result.start, result.end)

        Glide.with(thumbnail.context)
            .load(result.media.image.small)
            .into(thumbnail)
    }
}