package com.shahzar.enoclink.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.shahzar.enoclink.NavMgr
import com.shahzar.enoclink.R
import com.shahzar.enoclink.util.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val application: Application) {

    @Provides
    fun getString(): String {
        return application.getString(R.string.app_name)
    }

    @Provides
    fun getContext(): Context {
        return application
    }

    @Provides
    fun getNavMgr(): NavMgr {
        return NavMgr()
    }

    @Singleton
    @Provides
    fun getSharedPrefs(): SharedPreferences {
        return application.getSharedPreferences(Constants.SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    }

}