package com.example.presentation.ui


import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.presentation.ui.basket.BasketScreen
import com.example.presentation.ui.category.CategoryScreen
import com.example.presentation.ui.main.LikeScreen
import com.example.presentation.ui.main.MainCategoryScreen
import com.example.presentation.ui.main.MainHomeScreen
import com.example.presentation.ui.main.MyPageScreen
import com.example.presentation.ui.product_detail.ProductDetailScreen
import com.example.presentation.ui.search.SearchScreen
import com.example.presentation.utils.NavigationUtils
import com.example.presentation.viewmodel.MainViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient


@Composable
fun MainScreen(googleSignInClient: GoogleSignInClient) {
    val viewModel = hiltViewModel<MainViewModel>()
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    Scaffold(
        topBar = {
            if(MainNav.isMainRoute(currentRoute)) {
                MainHeader(viewModel = viewModel, navController = navController)
            }
        },
        scaffoldState = scaffoldState,
        bottomBar = {
            if(MainNav.isMainRoute(currentRoute)) {
                MainBottomNavigationBar(navController, currentRoute)
            }
        }
    ) {
        MainNavigationScreen(viewModel = viewModel, navController = navController, googleSignInClient, scaffoldState)
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
            IconButton(onClick = {
                viewModel.openBasket(navController)
            }) {
                Icon(Icons.Filled.ShoppingCart, "BasketIcon")

            }
        }
    )
}

@Composable
fun MainBottomNavigationBar(navController: NavHostController, currentRoute: String?) {
    val bottomNavigationItems = listOf(
        MainNav.Home,
        MainNav.Category,
        MainNav.MyPage,
        MainNav.Like,
    )

    BottomNavigation {

        bottomNavigationItems.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, item.route) },
                selected = currentRoute == item.route,
                onClick = {
                    NavigationUtils.navigate(
                        navController, item.route,
                        navController.graph.startDestinationRoute
                    )
                }
            )
        }
    }
}

@Composable
fun MainNavigationScreen(
    viewModel: MainViewModel,
    navController: NavHostController,
    googleSignInClient: GoogleSignInClient,
    scaffoldState: ScaffoldState
) {
    NavHost(navController = navController, startDestination = MainNav.Home.route) {
        composable(
            route = MainNav.Home.route,
            deepLinks = MainNav.Home.deepLinks
        ) {
            MainHomeScreen(navController, viewModel)
        }
        composable(
            route = MainNav.Category.route,
            deepLinks = MainNav.Category.deepLinks
        ) {
            MainCategoryScreen(viewModel, navController)
        }
        composable(
            route = MainNav.Like.route,
            deepLinks = MainNav.Like.deepLinks
        ) {
            LikeScreen(navController, viewModel)
        }
        composable(
            route = MainNav.MyPage.route,
            deepLinks = MainNav.MyPage.deepLinks
        ) {
            MyPageScreen(viewModel = viewModel, googleSignInClient = googleSignInClient)
        }
        composable(
            route = BasketNav.route,
            deepLinks = BasketNav.deepLinks
        ) {
            BasketScreen(scaffoldState)
        }
        composable(SearchNav.route) {
            SearchScreen(navController)
        }
        composable(
            CategoryNav.routeWithArgName(),
            arguments = CategoryNav.arguments,
            deepLinks = CategoryNav.deepLinks
        ) {
            val categoryString = it.arguments?.getString("category")
            val category = CategoryNav.findArgument(it)
            if(category != null) {
                CategoryScreen(category = category, navHostController = navController)
            }
        }
        composable(
            route = ProductDetailNav.routeWithArgName(),
            arguments = ProductDetailNav.arguments,
            deepLinks = ProductDetailNav.deepLinks
        ) {
            val productString = ProductDetailNav.findArgument(it)
            if (productString != null) {
                ProductDetailScreen(productString)
            }
        }
    }
}
