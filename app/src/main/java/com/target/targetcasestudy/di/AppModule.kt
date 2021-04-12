package com.target.targetcasestudy.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.target.targetcasestudy.TargetApp
import com.target.targetcasestudy.helpers.AppConstants
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
        httpClient.callTimeout(5, TimeUnit.SECONDS)
        httpClient.connectTimeout(5, TimeUnit.SECONDS)
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

}