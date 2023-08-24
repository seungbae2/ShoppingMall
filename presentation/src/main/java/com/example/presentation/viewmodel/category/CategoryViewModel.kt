package com.example.presentation.viewmodel.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.domain.model.Category
import com.example.domain.model.Product
import com.example.domain.usecase.CategoryUseCase
import com.example.presentation.delegate.ProductDelegate
import com.example.presentation.model.ProductVM
import com.example.presentation.ui.ProductDetailNav
import com.example.presentation.utils.NavigationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel  @Inject constructor(
    private val useCase: CategoryUseCase
) : ViewModel(), ProductDelegate {
    private val _products = MutableStateFlow<List<ProductVM>>(listOf())
    val products : StateFlow<List<ProductVM>> = _products

    suspend fun updateCategory(category: Category) {
        useCase.getProductsByCategory(category).collectLatest {
            _products.emit(convertToPresentationVM(it))
        }
    }

    override fun openProduct(navHostController: NavHostController, product: Product) {
        NavigationUtils.navigate(navHostController, ProductDetailNav.navigateWithArg(product.productId))
    }

    override fun likeProduct(product: Product) {
        viewModelScope.launch {
            useCase.likeProduct(product)
        }
    }

    private fun convertToPresentationVM(list: List<Product>): List<ProductVM> {
        return list.map {
            ProductVM(it, this)
        }
    }
}