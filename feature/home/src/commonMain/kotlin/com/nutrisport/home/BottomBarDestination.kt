package com.nutrisport.home

import com.example.nutrisport.Screen

enum class BottomBarDestination (
    val icon: Int,
    val title: String,
    val screen: Screen
){
    ProductsOverview(
        icon = 0,
        title = "Products",
        Screen.ProductsOverview
    ),
    Cart(
        icon = 0,
        title = "Cart",
        Screen.Cart

    ),
    Categories(
        icon = 0,
        title = "Categories",
        Screen.Categories
    )
}