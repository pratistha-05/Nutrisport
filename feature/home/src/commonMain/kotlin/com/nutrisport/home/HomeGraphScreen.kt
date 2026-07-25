package com.nutrisport.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nutrisport.utils.Screen
import com.example.nutrisport.data.Resources
import com.nutrisport.home.component.BottomBar
import com.nutrisport.home.sidenav.CustomDrawer
import com.nutrisport.home.sidenav.CustomDrawerState
import com.example.nutrisport.utils.remote.NetworkResult
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeGraphScreen(
    navigateToAuth: () -> Unit,
    ) {
    val viewmodel = koinViewModel<HomeGraphViewmodel>()
    val signOutState by viewmodel.signOutState.collectAsState()

    LaunchedEffect(signOutState) {
        when (signOutState) {
            is NetworkResult.Success -> {
                navigateToAuth()
            }
            is NetworkResult.Error -> {
                // Show error message
            }
            else -> {}
        }
    }

    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val selectedDestination by remember {

        //to handle the back destination
        derivedStateOf {
            val route = currentRoute.value?.destination?.route.toString()
            when {
                route.contains(BottomBarDestination.ProductsOverview.screen.toString()) -> BottomBarDestination.ProductsOverview
                route.contains(BottomBarDestination.Cart.screen.toString()) -> BottomBarDestination.Cart
                route.contains(BottomBarDestination.Categories.screen.toString()) -> BottomBarDestination.Categories
                else -> BottomBarDestination.ProductsOverview
            }
        }
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                CustomDrawer(
                    onProfileClick = {
                        scope.launch { drawerState.close() }
                    },
                    onContactClick = {
                        scope.launch { drawerState.close() }
                    },
                    onSignOutClick = {
                        scope.launch { 
                            viewmodel.signout()
                            drawerState.close() 
                        }
                    },
                    onAdminClick = {
                        scope.launch { drawerState.close() }
                    },
                    state = if (drawerState.isOpen) CustomDrawerState.OPEN else CustomDrawerState.CLOSED
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text("Nutrisport")
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                painter = painterResource(Resources.Image.GoogleLogo),
                                contentDescription = null
                            )
                        }
                    }
                )
            },
            bottomBar = {
                BottomBar(
                    isSelected = selectedDestination,
                    onSelect = { destination ->
                        navController.navigate(destination.screen) {
                            launchSingleTop = true
                            popUpTo<Screen.ProductsOverview> {
                                //after clicking on back by default it should go to products overview screen
                                saveState = true
                                inclusive = false
                            }
                            restoreState = true
                        }
                    }
                )
            }
        ) { paddingValues ->
            NavHost(
                navController,
                startDestination = Screen.ProductsOverview,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable<Screen.ProductsOverview> {
//                ProductsOverviewScreen()
                }
                composable<Screen.Cart> {
//                CartScreen()
                }
                composable<Screen.Categories> {
//                CategoriesScreen()
                }
            }
        }
    }
}
