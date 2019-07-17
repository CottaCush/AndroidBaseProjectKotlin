package com.cottacush.android.androidbaseprojectkt.di

import android.content.Context
import android.content.SharedPreferences
import com.cottacush.android.androidbaseprojectkt.AccessTokenDataSource
import com.cottacush.android.androidbaseprojectkt.AccessTokenInterceptor
import com.cottacush.android.androidbaseprojectkt.BuildConfig
import com.cottacush.android.androidbaseprojectkt.PrefsUtils
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class CoreDataModule(private val appContext: Context) {


    @Provides
    fun provideAcessTokenDataSource(
        prefsUtils: PrefsUtils
    ): AccessTokenDataSource =
        AccessTokenDataSource(prefsUtils)


    @Provides
    @ExampleServiceAPI
    fun provideSpecificOkHttpClient(
        upstream: OkHttpClient,
        tokenDataSource: AccessTokenDataSource
    ): OkHttpClient {
        return upstream.newBuilder()
            .addInterceptor(AccessTokenInterceptor(tokenDataSource))
            .build()
    }

    @Provides
    fun provideGenericOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun providePrefsUtils(context: Context, gson: Gson): PrefsUtils =
        PrefsUtils(context, gson)


    @Provides
    @Singleton
    fun appContext(): Context = appContext
}
