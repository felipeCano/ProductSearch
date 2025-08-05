package com.test.productsearch.presentation.di

import com.test.productsearch.domain.repository.SearchProductRepository
import com.test.productsearch.domain.usescases.GetSearchedSearchProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetSearchedSearchProductUseCase(searchProductRepository: SearchProductRepository): GetSearchedSearchProductUseCase {
        return GetSearchedSearchProductUseCase(searchProductRepository)
    }
}