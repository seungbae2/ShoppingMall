package com.example.presentation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.domain.model.Category
import com.example.domain.model.Product
import com.example.presentation.ui.NavigationRouteName.CATEGORY
import com.example.presentation.ui.NavigationRouteName.MAIN_CATEGORY
import com.example.presentation.ui.NavigationRouteName.MAIN_HOME
import com.example.presentation.ui.NavigationRouteName.MAIN_MY_PAGE
import com.example.presentation.ui.NavigationRouteName.PRODUCT_DETAIL
import com.example.presentation.ui.NavigationRouteName.SEARCH

sealed class NavigationItem(open val route: String) {
    sealed class MainNav(override val route: String, val icon: ImageVector, val name: String) : NavigationItem(route) {
        object Home : MainNav(MAIN_HOME, Icons.Filled.Home,"Home")
        object Category : MainNav(MAIN_CATEGORY, Icons.Filled.Star,"Category")
        object MyPage : MainNav(MAIN_MY_PAGE, Icons.Filled.AccountBox,"MyPage")

        companion object {
            fun isMainRoute(route: String?) : Boolean {
                return when (route) {
                    MAIN_HOME, MAIN_CATEGORY, MAIN_MY_PAGE -> true
                    else -> false
                }
            }
        }
    }

    data class CategoryNav(val category: Category) : NavigationItem(CATEGORY)

    data class ProductDetailNav(val product: Product) : NavigationItem(PRODUCT_DETAIL)

    object SearchNav : NavigationItem(SEARCH)
}

object NavigationRouteName {
    const val MAIN_HOME = "main_home"
    const val MAIN_CATEGORY = "main_category"
    const val MAIN_MY_PAGE = "main_my_page"
    const val CATEGORY = "category"
    const val PRODUCT_DETAIL = "product_detail"
    const val SEARCH = "search"
}