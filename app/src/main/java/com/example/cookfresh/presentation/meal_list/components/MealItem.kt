package com.example.cookfresh.presentation.meal_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cookfresh.domain.MealModel

@Composable
fun MealItem(
    meal: MealModel,
    onItemClick: ()->Unit,
    modifier: Modifier = Modifier
) {
    val contentColor= if(isSystemInDarkTheme()) Color.White else Color.Black
    Row (
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            modifier = modifier
                .size(100.dp)
                .aspectRatio(1f)
                .background(Color.White)
                .border(1.dp,MaterialTheme.colorScheme.surfaceVariant),
            model= meal.thumb,
            contentDescription = null
        )
        Text(
            modifier = modifier.weight(1f),
            text= meal.name,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Light,
            fontSize = 16.sp,
            color = contentColor
        )
    }
}

