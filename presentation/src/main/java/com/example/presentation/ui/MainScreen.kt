package com.example.presentation.ui


import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.presentation.ui.main.CategoryScreen
import com.example.presentation.ui.main.HomeScreen
import com.example.presentation.ui.theme.ShoppingMallTheme
import com.example.presentation.viewmodel.MainViewModel


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ShoppingMallTheme {
        MainScreen()
    }
}

@Composable
fun MainScreen() {
    val viewModel = hiltViewModel<MainViewModel>()
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    Scaffold(
        topBar = {
            Header(viewModel)
        },
        scaffoldState = scaffoldState,
        bottomBar = {
            if(NavigationItem.MainNav.isMainRoute(currentRoute)) {
                MainBottomNavigationBar(navController, currentRoute)
            }
        }
    ) {
        MainNavigationScreen(viewModel = viewModel, navController = navController)
    }
}

@Composable
fun Header(viewModel : MainViewModel) {
    TopAppBar(
        title = { Text("My App") },
        actions = {
            IconButton(onClick = {
                viewModel.openSearchForm()
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
fun MainNavigationScreen(viewModel: MainViewModel, navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationRouteName.MAIN_HOME) {
        composable(NavigationRouteName.MAIN_HOME) {
            HomeScreen(viewModel)
        }
        composable(NavigationRouteName.MAIN_CATEGORY) {
            CategoryScreen(viewModel)
        }
        composable(NavigationRouteName.MAIN_MY_PAGE) {
            Text(text = "Hello MyPage")
        }
        composable(NavigationRouteName.CATEGORY) {
            Text(text = "Hello MyPage")
        }
    }
}
