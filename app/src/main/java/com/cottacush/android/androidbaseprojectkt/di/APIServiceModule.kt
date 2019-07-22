package com.cottacush.android.androidbaseprojectkt.di

import com.cottacush.android.androidbaseprojectkt.BuildConfig
import com.cottacush.android.androidbaseprojectkt.auth.*
import com.cottacush.android.androidbaseprojectkt.sample.apis.AccessTokenProviderImpl
import com.cottacush.android.androidbaseprojectkt.sample.apis.ExampleAPIAuthService
import com.cottacush.android.androidbaseprojectkt.sample.apis.ExampleApiService
import com.google.gson.Gson
import dagger.Lazy
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class APIServiceModule {

    @Provides
    @Named("ExampleService")
    @Singleton
    fun provideExampleServiceHttpClient(
        upstream: OkHttpClient,
        accessTokenProvider: AccessTokenProvider
    ): OkHttpClient {
        return upstream.newBuilder()
            .addInterceptor(AccessTokenInterceptor(accessTokenProvider))
            .authenticator(AccessTokenAuthenticator(accessTokenProvider))
            .build()
    }

    @Provides
    @Singleton
    fun provideExampleAPIAuthService(
        client: Lazy<OkHttpClient>, gson: Gson
    ): ExampleAPIAuthService {
        return Retrofit.Builder()
            .baseUrl(ExampleAPIAuthService.ENDPOINT)
            .client(client.get())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ExampleAPIAuthService::class.java)
    }

    @Provides
    @Singleton
    fun provideExampleAPIService(
        @Named("ExampleService") client: Lazy<OkHttpClient>,
        gson: Gson
    ): ExampleApiService {
        return Retrofit.Builder()
            .baseUrl(ExampleApiService.ENDPOINT)
            .client(client.get())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ExampleApiService::class.java)
    }

    @Provides
    @Named("ExampleService")
    fun provideAccessTokenProvider(accessTokenProvider: AccessTokenProviderImpl): AccessTokenProvider =
        accessTokenProvider

    @Provides
    @Singleton
    fun provideGenericOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Provides
    @Singleton
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
}