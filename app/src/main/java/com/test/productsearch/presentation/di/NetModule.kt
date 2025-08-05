package com.test.productsearch.presentation.di

import com.test.productsearch.data.api.SearchProductAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    private val BASE_URL = "https://axesso-walmart-data-service.p.rapidapi.com/"
    private val API_KEY = "fce0e15738msh6a87c0c9db9505cp14b74fjsn54bc768f3bc7"

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val authInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val requestWithApiKey = originalRequest.newBuilder()
                .header("Apikey", API_KEY)
                .build()
            chain.proceed(requestWithApiKey)
        }

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideSearchProductAPIService(retrofit: Retrofit): SearchProductAPIService {
        return retrofit.create(SearchProductAPIService::class.java)
    }
}