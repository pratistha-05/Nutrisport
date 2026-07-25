package com.nutrisport.home

import com.example.nutrisport.utils.Screen
import com.example.nutrisport.data.Resources
import org.jetbrains.compose.resources.DrawableResource

enum class BottomBarDestination (
    val icon: DrawableResource,
    val title: String,
    val screen: Screen
){
    ProductsOverview(
        icon = Resources.Icon.Home,
        title = "Products",
        Screen.ProductsOverview
    ),
    Cart(
        icon = Resources.Icon.ShoppingCart,
        title = "Cart",
        Screen.Cart

    ),
    Categories(
        icon = Resources.Icon.Categories,
        title = "Categories",
        Screen.Categories
    )
}