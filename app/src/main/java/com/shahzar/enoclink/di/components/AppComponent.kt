package com.shahzar.enoclink.di.components

import android.app.Application
import com.shahzar.enoclink.MainActivity
import com.shahzar.enoclink.di.ViewModelBuilder
import com.shahzar.enoclink.di.modules.AppModule
import com.shahzar.enoclink.di.modules.NetworkModule
import com.shahzar.enoclink.ui.home.HomeFragment
import com.shahzar.enoclink.ui.login.LoginFragment
import com.shahzar.enoclink.ui.splash.SplashActivity
import dagger.Component

@Component(modules = [AppModule::class, NetworkModule::class, ViewModelBuilder::class])
interface AppComponent {

    fun application(application: Application)
    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(loginFragment: LoginFragment)
    fun inject(splashActivity: SplashActivity)

}