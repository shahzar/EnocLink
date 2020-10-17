package com.shahzar.enoclink

import android.app.Application
import com.shahzar.enoclink.di.components.AppComponent
import com.shahzar.enoclink.di.components.DaggerAppComponent
import com.shahzar.enoclink.di.modules.AppModule

class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        appComponent.application(this)
    }

}