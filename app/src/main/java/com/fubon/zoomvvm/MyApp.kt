package com.fubon.zoomvvm

import android.app.Application
import com.fubon.zoomvvm.retrofit.ServiceFactory
import com.fubon.zoomvvm.retrofit.service.ZooService

class MyApp: Application() {

    companion object {
        var zooService: ZooService? = null
    }

    override fun onCreate() {
        super.onCreate()
        zooService = ServiceFactory.getZooService()
    }
}