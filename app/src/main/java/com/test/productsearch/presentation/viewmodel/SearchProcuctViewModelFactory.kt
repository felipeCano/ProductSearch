package com.test.productsearch.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.productsearch.domain.usescases.GetSearchedSearchProductUseCase

class SearchProcuctViewModelFactory(
    private val app: Application,
    private val getSearchedSearchProductUseCase: GetSearchedSearchProductUseCase
) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchProcuctViewModel(
        app,
        getSearchedSearchProductUseCase
        ) as T
    }
}