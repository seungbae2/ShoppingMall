package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.domain.model.*
import com.example.domain.usecase.AccountUseCase
import com.example.domain.usecase.CategoryUseCase
import com.example.domain.usecase.LikeUseCase
import com.example.domain.usecase.MainUseCase
import com.example.presentation.delegate.BannerDelegate
import com.example.presentation.delegate.CategoryDelegate
import com.example.presentation.delegate.ProductDelegate
import com.example.presentation.model.*
import com.example.presentation.ui.CategoryNav
import com.example.presentation.ui.NavigationRouteName
import com.example.presentation.utils.NavigationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainUseCase: MainUseCase,
    categoryUseCase: CategoryUseCase,
    private val accountUseCase: AccountUseCase,
    likeUseCase: LikeUseCase
) : ViewModel(), ProductDelegate, BannerDelegate, CategoryDelegate{

    private val _columnCount = MutableStateFlow(DEFAULT_COLUMN_COUNT)
    val columnCount : StateFlow<Int> = _columnCount

    val modelList = mainUseCase.getModelList().map(::convertToPresentationVM)
    val categories = categoryUseCase.getCategories()
    val accountInfo = accountUseCase.getAccountInfo()

    val likeProducts = likeUseCase.getLikeProducts().map(::convertToPresentationVM)

    fun openSearchForm(navHostController: NavHostController) {
        NavigationUtils.navigate(navHostController, NavigationRouteName.SEARCH)
    }

    fun openBasket(navHostController: NavHostController) {
        NavigationUtils.navigate(navHostController, NavigationRouteName.BASKET)
    }

    fun signIn(accountInfo: AccountInfo) {
        viewModelScope.launch {
            accountUseCase.signIn(accountInfo)
        }
    }

    fun signOut() {
        viewModelScope.launch {
            accountUseCase.signOut()
        }
    }

    fun updateColumnCount(count : Int) {
        viewModelScope.launch {
            _columnCount.emit(count)
        }
    }

    override fun likeProduct(product: Product) {
        viewModelScope.launch {
            mainUseCase.likeProduct(product)
        }
    }

    override fun openProduct(navHostController: NavHostController, product: Product) {
        NavigationUtils.navigate(navHostController, NavigationRouteName.PRODUCT_DETAIL, product)
    }

    override fun openBanner(bannerId: String) {

    }

    override fun openCategory(navHostController: NavHostController, category: Category) {
//        NavigationUtils.navigate(navHostController, NavigationRouteName.CATEGORY, category)
        NavigationUtils.navigate2(navHostController, CategoryNav.navigateWithArg(category))
    }

    private fun convertToPresentationVM(list: List<BaseModel>): List<PresentationVM<out BaseModel>> {
        return list.map {model ->
            when(model) {
                is Product -> ProductVM(model, this)
                is Ranking -> RankingVM(model, this)
                is Carousel -> CarouselVM(model, this)
                is Banner -> BannerVM(model, this)
                is BannerList -> BannerListVM(model, this)
            }
        }
    }

    companion object {
        private const val DEFAULT_COLUMN_COUNT = 2
    }


}