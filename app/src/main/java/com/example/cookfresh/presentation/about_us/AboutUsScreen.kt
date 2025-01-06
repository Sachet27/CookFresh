package com.example.cookfresh.presentation.about_us

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookfresh.R
import com.example.cookfresh.presentation.about_us.components.ImageItem
import com.example.cookfresh.presentation.components.TopBar
import com.example.ui.theme.CookFreshTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AboutUsScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(top= 8.dp).verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier= Modifier.fillMaxWidth().height(250.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.board),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Make Your\nCooking Journey Fun",
                textAlign = TextAlign.Center,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(Modifier.height(16.dp))
        Column (
            modifier = modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surfaceContainerLow).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Our Values",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
            FlowRow(
                modifier= modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                ImageItem(
                    size = 180.dp,
                    image = painterResource(R.drawable.reliable),
                    title = "Reliable Recipes"
                )
                ImageItem(
                    size = 180.dp,
                    image = painterResource(R.drawable.farm),
                    title = "Farm-Fresh Ingredients"
                )
                ImageItem(
                    size = 180.dp,
                    image = painterResource(R.drawable.deli),
                    title = "Delicious Possibilties"
                )
            }
        }
        Spacer(Modifier.height(8.dp))
        Column(
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surfaceContainerHigh).padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                textAlign = TextAlign.Center,
                text = "Making Cooking Easy",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier= Modifier.fillMaxWidth().padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.weight(0.4f).aspectRatio(1f),
                    painter = painterResource(R.drawable.salady),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                Text(
                    modifier = Modifier.weight(0.6f).padding(start = 8.dp),
                    text = "We believe everyone deserves fresh and healthy food, so we've made it easy for you to get started. Our mission is to help you make cooking fun and easy so you can enjoy delicious meals with your loved ones. Our team of chefs and nutritionists work hard to create recipes that are easy to follow and use fresh ingredients that are good for you.",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 14.sp,
                    color= MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
        Spacer(Modifier.height(8.dp))
        Column(
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surfaceContainerLow).padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = "Follow Us At",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.instagram),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.facebook),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.tiktok),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun AboutUs() {
    CookFreshTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            topBar = {
                TopBar {
                }
            }
        ) { padding ->
            Box(
                Modifier.fillMaxSize().padding(padding)
            ){
                AboutUsScreen()
            }
        }
    }
}
