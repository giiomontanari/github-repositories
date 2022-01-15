package com.example.gitrepositories.di

import com.example.gitrepositories.data.GitRepositoriesApi
import com.example.gitrepositories.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideApiService(get(), GitRepositoriesApi::class.java) }
}

fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient).build()

fun provideApiService(retrofit: Retrofit, apiService: Class<GitRepositoriesApi>) =
    createService(retrofit, apiService)

fun <T> createService(retrofit: Retrofit, serviceClass: Class<T>): T = retrofit.create(serviceClass)
