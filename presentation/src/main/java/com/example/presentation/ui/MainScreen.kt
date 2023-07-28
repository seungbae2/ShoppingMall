package com.example.presentation.ui


import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.domain.model.Category
import com.example.presentation.ui.category.CategoryScreen
import com.example.presentation.ui.main.MainCategoryScreen
import com.example.presentation.ui.main.MainHomeScreen
import com.example.presentation.ui.main.MyPageScreen
import com.example.presentation.ui.product_detail.ProductDetailScreen
import com.example.presentation.ui.search.SearchScreen
import com.example.presentation.ui.theme.ShoppingMallTheme
import com.example.presentation.viewmodel.MainViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.gson.Gson


@Composable
fun MainScreen(googleSignInClient: GoogleSignInClient) {
    val viewModel = hiltViewModel<MainViewModel>()
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    Scaffold(
        topBar = {
            if(NavigationItem.MainNav.isMainRoute(currentRoute)) {
                MainHeader(viewModel = viewModel, navController = navController)
            }
        },
        scaffoldState = scaffoldState,
        bottomBar = {
            if(NavigationItem.MainNav.isMainRoute(currentRoute)) {
                MainBottomNavigationBar(navController, currentRoute)
            }
        }
    ) {
        MainNavigationScreen(viewModel = viewModel, navController = navController, googleSignInClient)
    }
}

@Composable
fun MainHeader(viewModel : MainViewModel, navController: NavHostController) {
    TopAppBar(
        title = { Text("My App") },
        actions = {
            IconButton(onClick = {
                viewModel.openSearchForm(navController)
            }) {
                Icon(Icons.Filled.Search, "SearchIcon")
                
            }
        }
    )
}

@Composable
fun MainBottomNavigationBar(navController: NavController, currentRoute: String?) {
    val bottomNavigationItems = listOf(
        NavigationItem.MainNav.Home,
        NavigationItem.MainNav.Category,
        NavigationItem.MainNav.MyPage,
        NavigationItem.MainNav.MyLike,
    )

    BottomNavigation {

        bottomNavigationItems.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, item.route) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun MainNavigationScreen(viewModel: MainViewModel, navController: NavHostController, googleSignInClient: GoogleSignInClient) {
    NavHost(navController = navController, startDestination = NavigationRouteName.MAIN_HOME) {
        composable(NavigationRouteName.MAIN_HOME) {
            MainHomeScreen(navController, viewModel)
        }
        composable(NavigationRouteName.MAIN_CATEGORY) {
            MainCategoryScreen(viewModel, navController)
        }
        composable(NavigationRouteName.MAIN_MY_PAGE) {
            MyPageScreen(viewModel = viewModel, googleSignInClient = googleSignInClient)
        }
        composable(NavigationRouteName.MAIN_LIKE) {

        }
        composable(NavigationRouteName.CATEGORY + "/{category}",
        arguments = listOf(navArgument("category") { type = NavType.StringType})
        ) {
            val categoryString = it.arguments?.getString("category")
            val category = Gson().fromJson(categoryString, Category::class.java)
            if(category != null) {
                CategoryScreen(category = category, navHostController = navController)
            }
        }
        composable(NavigationRouteName.PRODUCT_DETAIL+"/{product}",
        arguments = listOf(navArgument("product"){ type = NavType.StringType })
        ) {
            val productString = it.arguments?.getString("product")

            if(productString != null) {
                ProductDetailScreen(productString)
            }
        }
        composable(NavigationRouteName.SEARCH) {
            SearchScreen(navController)
        }
    }
}
