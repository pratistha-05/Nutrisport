package com.nutrisport.home.sidenav

import com.example.nutrisport.data.Resources
import org.jetbrains.compose.resources.DrawableResource

enum class DrawerItem(
    val title:String,
    val icon: DrawableResource
){
    Profile(
        title = "Profile",
        icon = Resources.Icon.Person
    ),
    Blog(
        title = "Blog",
        icon = Resources.Icon.MapPin
    ),
    Locations(
        title = "Locations",
        icon = Resources.Icon.MapPin
    ),
    Contact(
        title = "Contact Us",
        icon = Resources.Image.GoogleLogo
    ),
    SignOut(
        title = " Sign Out",
        icon = Resources.Icon.SignOut
    ),
    Admin(
        title = " Admin",
        icon =Resources.Icon.Unlock
    ),
}