package com.shahzar.enoclink.di.modules

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.gson.GsonBuilder
import com.shahzar.enoclink.data.local.UserPreferences
import com.shahzar.enoclink.data.mock.MockApiServiceImpl
import com.shahzar.enoclink.data.remote.ApiService
import com.shahzar.enoclink.data.remote.GravatarApiService
import com.shahzar.enoclink.util.interceptor.SessionInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    private val BASE_URL = ""
    private val GRAVATAR_URL = "https://www.gravatar.com/"


    @Provides
    fun provideApiService(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): ApiService {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build().create(ApiService::class.java)

    }

    @Provides
    fun provideGravatarApiService(@Named("NoAuth") okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): GravatarApiService {
        return Retrofit.Builder()
            .baseUrl(GRAVATAR_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build().create(GravatarApiService::class.java)

    }

    @Provides
    @Named("MockApi")
    fun provideMockApiService(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory, prefs: UserPreferences): ApiService {
        return MockApiServiceImpl(prefs)
    }

    @Provides
    fun provideOkHttp(interceptor: SessionInterceptor): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor(interceptor)
        return okHttpBuilder.build()
    }

    @Provides
    @Named("NoAuth")
    fun provideNoAuthOkHttp(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        return okHttpBuilder.build()
    }

    @Singleton
    @Provides
    fun provideInterceptor(): SessionInterceptor {
        return SessionInterceptor()
    }

    @Provides
    fun provideGson(): GsonConverterFactory = GsonConverterFactory.create(GsonBuilder().create())

    @Provides
    fun provideGlideRequestManager(context: Context, requestOptions: RequestOptions): RequestManager {
        return Glide.with(context)
            .setDefaultRequestOptions(requestOptions)
    }

    @Provides
    fun providesGlideRequestOptions(): RequestOptions {
        return RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
    }

}