package com.dineshworkspace.datingapp.di

import com.dineshworkspace.datingapp.discover.DiscoverRepository
import com.dineshworkspace.datingapp.helpers.AppConstants
import com.dineshworkspace.datingapp.helpers.DataSource
import com.dineshworkspace.datingapp.helpers.ErrorUtil
import com.dineshworkspace.datingapp.network.ApiHelper
import com.dineshworkspace.datingapp.network.ApiHelperImpl
import com.dineshworkspace.datingapp.network.ApiService
import com.dineshworkspace.datingapp.phoneVerification.PhoneNumberValidationRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

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

        httpClient.networkInterceptors().add(Interceptor { chain ->
            val requestBuilder: Request.Builder = chain.request().newBuilder()
            requestBuilder.header(
                AppConstants.API_HEADER_KEY_CONTENT_TYPE,
                AppConstants.API_HEADER_VALUE_CONTENT_TYPE
            )
            requestBuilder.header(
                AppConstants.API_HEADER_KEY_COOKIE,
                AppConstants.API_HEADER_VALUE_COOKIE
            )

            chain.proceed(requestBuilder.build())
        })

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

    @[Provides Singleton]
    fun providesErrorUtil(retrofit: Retrofit): ErrorUtil {
        return ErrorUtil(retrofit = retrofit)
    }

    @[Provides Singleton]
    fun providesDataSource(apiService: ApiService, errorUtil: ErrorUtil): DataSource {
        return DataSource(apiService = apiService, errorUtil = errorUtil)
    }

    @[Provides Singleton]
    fun providesPhoneNumberRepository(
        dataSource: DataSource
    ): PhoneNumberValidationRepository {
        return PhoneNumberValidationRepository(dataSource = dataSource)
    }

    @[Provides Singleton]
    fun providesDiscoverRepository(
        dataSource: DataSource
    ): DiscoverRepository {
        return DiscoverRepository(dataSource = dataSource)
    }


}