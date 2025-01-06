package com.example.cookfresh.presentation.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookfresh.R
import com.example.ui.theme.CookFreshTheme

@Composable
fun NavDrawer(
    enabled:Boolean= true,
    drawerState: DrawerState,
    items: List<NavigationItems>,
    modifier: Modifier= Modifier,
    onItemClick: (NavigationItems)->Unit,
    content: @Composable () -> Unit
){
    var selectedItemId by remember{ mutableStateOf(DrawerItems.HOME) }
    if(isSystemInDarkTheme()) Color.White else Color.Black
    ModalNavigationDrawer(
        gesturesEnabled = enabled,
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet (
                modifier = Modifier
                    .width(300.dp)    // or your desired width
                    .fillMaxHeight(),
            ){
                Box(
                    modifier = Modifier.fillMaxWidth().weight(0.3f),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(R.drawable.drawer_banner),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.logo),
                            contentDescription = null,
                            tint= Color.Unspecified,
                            modifier = Modifier.size(120.dp)
                        )
                        Text(
                            text = "Cook Fresh",
                            fontFamily = FontFamily(Font(R.font.leckerlione_regular)),
                            style = MaterialTheme.typography.headlineLarge.copy(fontSize = 35.sp),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                Column(
                    modifier= Modifier.weight(1f)
                ){
                    HorizontalDivider()
                    Spacer(Modifier.height(8.dp))
                    items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        },
                        selected = item.id == selectedItemId,
                        onClick = {
                            selectedItemId= item.id
                            onItemClick(item)
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = null
                            )
                        }
                    )
                }
            }
            }
        }
    ) {
        content()
    }
}

@Preview
@Composable
private fun SideDrawerPreview() {
    CookFreshTheme {
        NavDrawer(
            drawerState = rememberDrawerState(DrawerValue.Open),
            items = SideDrawerItemsList.drawerItemsList,
            modifier = Modifier,
            onItemClick = {},
        ) { }
    }
}