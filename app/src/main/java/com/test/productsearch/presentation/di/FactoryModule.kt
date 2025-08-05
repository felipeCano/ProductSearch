package com.test.productsearch.presentation.di

import android.app.Application
import com.test.productsearch.domain.usescases.GetSearchedSearchProductUseCase
import com.test.productsearch.presentation.viewmodel.SearchProcuctViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideSearchProductViewModelFactory(
        application: Application,
        getSearchedSearchProductUseCase: GetSearchedSearchProductUseCase
    ): SearchProcuctViewModelFactory {
        return SearchProcuctViewModelFactory(
            application,
            getSearchedSearchProductUseCase
        )
    }
}