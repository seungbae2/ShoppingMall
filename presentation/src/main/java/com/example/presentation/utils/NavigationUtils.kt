package com.example.presentation.utils

import androidx.navigation.NavHostController
import com.example.presentation.ui.BasketNav
import com.example.presentation.ui.CategoryNav
import com.example.presentation.ui.Destination
import com.example.presentation.ui.MainNav
import com.example.presentation.ui.NavigationRouteName
import com.example.presentation.ui.ProductDetailNav
import com.example.presentation.ui.SearchNav

object NavigationUtils {

    fun navigate(
        controller: NavHostController,
        routeName: String,
        backStackRouteName: String? = null,
        isLaunchSingleTop: Boolean = true,
        needToRestoreState: Boolean = true
    ) {
        controller.navigate(routeName) {
            if(backStackRouteName != null) {
                popUpTo(backStackRouteName) { saveState = true }
            }
            launchSingleTop = isLaunchSingleTop
            restoreState = needToRestoreState
        }
    }

    fun findDestination(route : String?) : Destination {
        return when(route) {
            NavigationRouteName.MAIN_MY_PAGE -> MainNav.MyPage
            NavigationRouteName.MAIN_LIKE -> MainNav.Like
            NavigationRouteName.MAIN_HOME -> MainNav.Home
            NavigationRouteName.MAIN_CATEGORY -> MainNav.Category
            NavigationRouteName.PRODUCT_DETAIL -> ProductDetailNav
            NavigationRouteName.CATEGORY -> CategoryNav
            NavigationRouteName.SEARCH -> SearchNav
            NavigationRouteName.BASKET -> BasketNav

            ProductDetailNav.routeWithArgName() -> ProductDetailNav
            CategoryNav.routeWithArgName() -> CategoryNav
            else -> MainNav.Home
        }
    }
}