package com.example.news.base

import android.app.Application
import com.example.data.data.offline.MyDataBase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class App : Application()