package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.domain.model.Banner
import com.example.domain.model.BannerList
import com.example.domain.model.Category
import com.example.domain.model.Product
import com.example.domain.usecase.CategoryUseCase
import com.example.domain.usecase.MainUseCase
import com.example.presentation.ui.NavigationRouteName
import com.example.presentation.utils.NavigationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    mainUseCase: MainUseCase,
    categoryUseCase: CategoryUseCase
) : ViewModel() {

    private val _columnCount = MutableStateFlow(DEFAULT_COLUMN_COUNT)
    val columnCount : StateFlow<Int> = _columnCount

    val modelList = mainUseCase.getModelList()
    val categories = categoryUseCase.getCategories()
    fun openSearchForm() {
        println("openSearchForm")
    }

    fun updateColumnCount(count : Int) {
        viewModelScope.launch {
            _columnCount.emit(count)
        }
    }

    fun openProduct(product: Product) {

    }

    fun openCarouselProduct(product: Product) {

    }

    fun openBanner(banner: Banner) {

    }

    fun openBannerList(bannerList: BannerList) {

    }

    fun openRankingProduct(product: Product) {

    }

    fun openCategory(navHostController: NavHostController, category: Category) {
        NavigationUtils.navigate(navHostController, NavigationRouteName.CATEGORY, category)
    }

    companion object {
        private const val DEFAULT_COLUMN_COUNT = 2
    }
}