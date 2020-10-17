package com.shahzar.enoclink.di.modules

import android.app.Application
import com.shahzar.enoclink.NavMgr
import com.shahzar.enoclink.R
import dagger.Module
import dagger.Provides

@Module
class AppModule(val application: Application) {

    @Provides
    fun getString(): String {
        return application.getString(R.string.app_name)
    }

    @Provides
    fun getNavMgr(): NavMgr {
        return NavMgr()
    }

}