package com.test.dvt.di

import android.content.Context
import androidx.room.Room
import com.test.dvt.core.network_connectivity.ConnectivityObserver
import com.test.dvt.core.network_connectivity.NetworkConnectivityObserver
import com.test.dvt.core.utils.Constants
import com.test.dvt.data.datasource.OpenWeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainAppModule {

    private val api_interceptor = Interceptor {
        val originalRequest = it.request()
        val newHttpUrl = originalRequest.url.newBuilder()
            .addQueryParameter("appid", Constants.API_KEY)
            .build()
        val newRequest = originalRequest.newBuilder()
            .url(newHttpUrl)
            .build()
        it.proceed(newRequest)
    }

    private val loggingInterceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(api_interceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideOpenWeatherAPI(): OpenWeatherService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenWeatherService::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkConnectivityService(@ApplicationContext applicationContext: Context): ConnectivityObserver {
        return NetworkConnectivityObserver(applicationContext)
    }

}