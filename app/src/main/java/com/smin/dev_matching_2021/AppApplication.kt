package com.smin.dev_matching_2021

import android.app.Application
import com.smin.dev_matching_2021.repository.Repository

class AppApplication : Application() {
    val repository = Repository
}