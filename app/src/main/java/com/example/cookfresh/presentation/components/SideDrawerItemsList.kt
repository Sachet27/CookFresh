package com.example.cookfresh.presentation.components

object SideDrawerItemsList {
    val drawerItemsList= DrawerItems.list.map {
        NavigationItems(
            id = it,
            title = it.toSelectionString(),
            icon = it.toIcon(),
        )
    }
}