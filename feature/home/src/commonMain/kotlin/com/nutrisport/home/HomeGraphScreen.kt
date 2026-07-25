package com.nutrisport.home

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nutrisport.Screen
import com.nutrisport.home.component.BottomBar
import io.ktor.websocket.Frame
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeGraphScreen() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState()

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
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                   Text("Nutrisport")
                },
                navigationIcon ={
                    IconButton(onClick = {}){
                        Icon(painter = painterResource(), contentDescription = null))
                    }
                }
            )
        },
        bottomBar = {
            BottomBar(
                isSelected = selectedDestination,
                onSelect = { destination ->
                    navController.navigate(destination.screen){
                        launchSingleTop = true
                        popUpTo < Screen.ProductsOverview>{
                            //after clicking on back by default it should go to products overview screen
                            saveState = true
                            inclusive = false
                        }
                        restoreState = true
                    }
                }
            )
        }
    ) {
        NavHost(navController,
            startDestination = Screen.ProductsOverview){
            composable<Screen.ProductsOverview>{
//                ProductsOverviewScreen()
            }
            composable<Screen.Cart>{
//                CartScreen()
            }
            composable<Screen.Categories>{
//                CategoriesScreen()
            }
        }
    }
}