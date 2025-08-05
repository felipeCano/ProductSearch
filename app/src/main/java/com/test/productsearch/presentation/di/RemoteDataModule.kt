package com.test.productsearch.presentation.di

import com.test.productsearch.data.api.SearchProductAPIService
import com.test.productsearch.data.repository.dataSource.SearchProductRemoteDataSource
import com.test.productsearch.data.repository.dataSourceImpl.SearchProductRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideSearchProductRemoteDataSource(
        searchProductAPIService: SearchProductAPIService
    ): SearchProductRemoteDataSource {
        return SearchProductRemoteDataSourceImpl(searchProductAPIService)
    }
}