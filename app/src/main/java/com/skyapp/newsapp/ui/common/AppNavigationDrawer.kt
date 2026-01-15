package com.skyapp.newsapp.ui.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.ModalWideNavigationRail
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp


@Composable
fun AppNavigationDrawer(
    modifier : Modifier = Modifier,
    drawerContent : @Composable () -> Unit,
    drawerState : DrawerState = DrawerState(DrawerValue.Closed),
    gesturesEnabled : Boolean = true,
    scrimColor : Color = DrawerDefaults.scrimColor,
    drawerContainerColor : Color = Color.Transparent,
    drawerContentColor : Color = Color.Black,
    drawerShape : Shape = RoundedCornerShape(0.dp),
    content : @Composable () -> Unit,
) {

    ModalNavigationDrawer(
        gesturesEnabled = gesturesEnabled,
        scrimColor = scrimColor,
        drawerState = drawerState,
        modifier = modifier,
       drawerContent = {
           ModalDrawerSheet(
              drawerContainerColor = drawerContainerColor,
               drawerContentColor = drawerContentColor,
               drawerShape = drawerShape,
               content =  {
                   drawerContent.invoke()
               }
           )
       },
        content =  {
            content.invoke()
        }
    )

}