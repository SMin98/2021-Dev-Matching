package com.smin.dev_matching_2021.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Detail(
    @SerializedName("ai_sec_yn")
    val aiSecYn: String,
    @SerializedName("audit_yn")
    val auditYn: String,
    @SerializedName("basic_science_sec_yn")
    val basicScienceSecYn: String,
    @SerializedName("blocks_url")
    val blocksUrl: String,
    val classfy: String,
    @SerializedName("classfy_name")
    val classfyName: String,
    @SerializedName("classfy_plus")
    val classfyPlus: String,
    @SerializedName("course_id")
    val courseId: String,
    @SerializedName("course_period")
    val coursePeriod: String,
    val created: String,
    val effort: String,
    @SerializedName("effort_time")
    val effortTime: String,
    val end: Date,
    @SerializedName("enrollment_end")
    val enrollmentEnd: String,
    @SerializedName("enrollment_start")
    val enrollmentStart: String,
    @SerializedName("fourth_industry_yn")
    val fourthIndustryYn: String,
    val hidden: Boolean,
    @SerializedName("home_course_step")
    val homeCourseStep: Any?,
    @SerializedName("home_course_yn")
    val homeCourseYn: Any?,
    val id: String,
    @SerializedName("invitation_only")
    val invitationOnly: Boolean,
    @SerializedName("job_edu_yn")
    val jobEduYn: String,
    @SerializedName("language_name")
    val languageName: String,
    @SerializedName("learning_time")
    val learningTime: String,
    val level: String,
    val linguistics: String,
    val media: MediaX,
    @SerializedName("middle_classfy")
    val middleClassfy: String,
    @SerializedName("middle_classfy_name")
    val middleClassfyName: String,
    @SerializedName("mobile_available")
    val mobileAvailable: Boolean,
    val modified: String,
    val name: String,
    val number: String,
    val org: String,
    @SerializedName("org_name")
    val orgName: String,
    val overview: String,
    val pacing: String,
    @SerializedName("passing_grade")
    val passingGrade: String,
    @SerializedName("preview_video")
    val previewVideo: Any?,
    @SerializedName("ribbon_yn")
    val ribbonYn: String,
    @SerializedName("short_description")
    val shortDescription: String,
    val start: Date,
    @SerializedName("start_display")
    val startDisplay: String,
    @SerializedName("start_type")
    val startType: String,
    val teachers: String,
    @SerializedName("video_time")
    val videoTime: String,
    val week: String
)