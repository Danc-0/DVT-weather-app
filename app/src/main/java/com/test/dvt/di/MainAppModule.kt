package com.test.dvt.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.test.dvt.core.network_connectivity.ConnectivityObserver
import com.test.dvt.core.network_connectivity.NetworkConnectivityObserver
import com.test.dvt.core.utils.Constants
import com.test.dvt.data.OpenWeatherService
import com.test.dvt.data.datasource.local.AppDatabase
import com.test.dvt.data.datasource.remote.CurrentWeatherRepositoryImpl
import com.test.dvt.domain.repository.CurrentWeatherRepository
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

    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                ""
            )
        }
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext application: Context): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "bored_activity"
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkConnectivityService(@ApplicationContext applicationContext: Context): ConnectivityObserver {
        return NetworkConnectivityObserver(applicationContext)
    }

    @Provides
    @Singleton
    fun provideCurrentWeatherRepository(openWeatherService: OpenWeatherService, appDatabase: AppDatabase): CurrentWeatherRepository {
        return CurrentWeatherRepositoryImpl(openWeatherService, weatherDao = appDatabase.weatherDao)
    }

}