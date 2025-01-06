package com.example.cookfresh.presentation.mealdetail

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.breens.beetablescompose.BeeTablesCompose
import com.example.cookfresh.domain.MealModel
import com.example.cookfresh.presentation.MealActions
import com.example.cookfresh.presentation.MealState
import com.example.cookfresh.presentation.components.TopBar
import com.example.core.presentation.DataEntry
import com.example.core.presentation.formatInstructions
import com.example.ui.theme.CookFreshTheme

@Composable
fun MealDescriptionScreen(
    state: MealState,
    onAction: (MealActions)->Unit,
    modifier: Modifier= Modifier
) {
    if(state.isLoading){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    }
    else{
        val meal= state.mealDescription!!
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.4f),
                model = meal.thumb,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier= modifier
                    .padding(16.dp)
                    .weight(0.6f)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(Modifier.height(16.dp))
                Text(
                    text = meal.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Row (
                    modifier= Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Category: ${meal.category}",
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "Area: ${meal.area}",
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), thickness = 2.dp)

                Text(
                    text = "Instructions:",
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = formatInstructions(meal.instructions)
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp), thickness = 2.dp)

                Text(
                    text = "Ingredients:",
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(4.dp)
                )
                val tableEntries= (meal.strIngredients).zip(meal.strMeasures).mapIndexed { index, pair -> DataEntry(index+1, pair.first, pair.second) }
                Spacer(Modifier.height(8.dp))
                BeeTablesCompose(
                    data = tableEntries,
                    enableTableHeaderTitles = true,
                    headerTableTitles = listOf("S.N", "Ingredients", "Measures"),
                    headerTitlesBorderColor = MaterialTheme.colorScheme.onSurface,
                    headerTitlesTextStyle = MaterialTheme.typography.titleMedium,
                    headerTitlesBackGroundColor = MaterialTheme.colorScheme.surfaceVariant,
                    rowBorderColor = MaterialTheme.colorScheme.onSurface,
                    rowTextStyle = MaterialTheme.typography. bodyMedium,
                    borderStroke = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
                    horizontalDividerColor = MaterialTheme.colorScheme.onSurface,
                    contentAlignment = Alignment.Center,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


