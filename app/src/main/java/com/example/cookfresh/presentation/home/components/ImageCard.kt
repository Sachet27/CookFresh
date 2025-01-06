package com.example.cookfresh.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cookfresh.domain.MealCategoryModel

@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    meal: MealCategoryModel,
    onClick: ()-> Unit
) {
    Column(
        modifier = modifier.padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(150.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(15.dp))
                .clickable {
                    onClick()
                }

                .border(2.dp,
                    MaterialTheme.colorScheme.onSurfaceVariant,
                    RoundedCornerShape(15.dp)
                )
                .background(Color.White)
        ) {
            AsyncImage(
               modifier= Modifier.fillMaxSize(),
                model = meal.thumb,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
            )
        }
        Spacer(Modifier.height(4.dp))
        Text(
            text = meal.name,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ImageCard(
        meal= MealCategoryModel(2,"dsads","adsdsa", "")
    ) {

    }
}