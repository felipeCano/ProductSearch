package com.test.productsearch.presentation.di

import com.test.productsearch.data.repository.SearchProductRepositoryImpl
import com.test.productsearch.data.repository.dataSource.SearchProductRemoteDataSource
import com.test.productsearch.domain.repository.SearchProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideSearchProductRepository(
        searchProductRemoteDataSource: SearchProductRemoteDataSource
    ): SearchProductRepository {
        return SearchProductRepositoryImpl(searchProductRemoteDataSource)
    }
}