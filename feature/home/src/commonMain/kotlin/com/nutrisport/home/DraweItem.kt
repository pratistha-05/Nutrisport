package com.nutrisport.home

import org.jetbrains.compose.resources.DrawableResource

enum class DrawerItem(
    val title:String,
    val icon: DrawableResource
){
    Profile(
        title = "Profile",
        icon = DrawerIcons.Profile
    ),
    Blog(
        title = "Blog",
        icon = DrawerIcons.Settings
    ),
    Locations(
        title = "Locations",
        icon = DrawerIcons.Logout
    ),
    Contact(
        title = "Contact Us",
        icon = DrawerIcons.Logout
    ),
    SignOut(
        title = " Sign Out",
        icon = DrawerIcons.Logout
    ),
    Admin(
        title = " Admin",
        icon = DrawerIcons.Logout
    ),
}