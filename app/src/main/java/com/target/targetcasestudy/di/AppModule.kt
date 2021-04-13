package com.target.targetcasestudy.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.target.targetcasestudy.TargetApp
import com.target.targetcasestudy.helpers.AppConstants
import com.target.targetcasestudy.network.ApiHelper
import com.target.targetcasestudy.network.ApiHelperImpl
import com.target.targetcasestudy.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @[Provides Singleton]
    fun providesApplication(): TargetApp {
        return TargetApp.application
    }

    @[Provides Singleton]
    fun providesGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @[Provides Singleton]
    fun providesOkHttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.callTimeout(10, TimeUnit.SECONDS)
        httpClient.connectTimeout(10, TimeUnit.SECONDS)
        return httpClient.build()
    }

    @[Provides Singleton]
    fun providesRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(AppConstants.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @[Provides Singleton]
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @[Provides Singleton]
    fun providesApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

}