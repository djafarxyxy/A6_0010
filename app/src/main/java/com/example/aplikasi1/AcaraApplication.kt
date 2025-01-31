package com.example.aplikasi1

import android.app.Application
import com.example.aplikasi1.data.di.AppContainer
import com.example.aplikasi1.data.di.AppContainerImpl

class AcaraApplications : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl()
    }
}
