package com.nutrisport.home.sidenav

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.nutrisport.IconPrimary
import com.example.nutrisport.TextPrimary
import com.nutrisport.shared.FontSize
import org.jetbrains.compose.resources.painterResource

@Composable
fun CustomDrawer(
    onProfileClick:()->Unit,
    onContactClick:()->Unit,
    onSignOutClick:()->Unit,
    onAdminClick:()->Unit,
    state:CustomDrawerState
){
    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp)){

        DrawerItem.entries.forEach {
            DrawerItemCard(drawerItem = it){
                when(it){
                    DrawerItem.Profile->onProfileClick()
                    DrawerItem.Contact->onContactClick()
                    DrawerItem.SignOut->onSignOutClick()
                    DrawerItem.Admin->onAdminClick()
                    else->{}
                }
            }
        }
            Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun DrawerItemCard(
    drawerItem: DrawerItem,
    onClick:()->Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(size = 99.dp))
            .clickable { onClick() }
            .padding(
                vertical = 12.dp,
                horizontal = 12.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(drawerItem.icon),
            contentDescription = "Drawer item icon",
            tint = IconPrimary
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = drawerItem.title,
            color = TextPrimary,
            fontSize = FontSize.EXTRA_REGULAR
        )
    }

}