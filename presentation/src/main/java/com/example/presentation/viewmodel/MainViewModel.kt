package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(useCase: MainUseCase) : ViewModel() {

    val productList = useCase.getProductList()
    fun openSearchForm() {
        println("openSearchForm")
    }
}