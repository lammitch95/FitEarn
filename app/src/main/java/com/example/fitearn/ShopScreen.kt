package com.example.fitearn

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.scale
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShopScreen(shopItems: List<ShopItem>, onPurchase: (ShopItem) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF833AB4), Color(0xFFFD1D1D), Color(0xFFFEDB5B))
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Avatar Shop",
            style = MaterialTheme.typography.headlineLarge.copy(color = Color.White),
            modifier = Modifier.padding(8.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(shopItems) { item ->
                ShopItemCard(item, onPurchase)
            }
        }
    }
}

@Composable
fun ShopItemCard(item: ShopItem, onPurchase: (ShopItem) -> Unit) {
    var isPressed by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(
        targetValue = if (item.isPurchased) Color(0xFFB0BEC5) else Color(0xFF7E57C2)
    )
    val buttonColor by animateColorAsState(
        targetValue = if (item.isPurchased) Color(0xFFC5CAE9) else Color(0xFFFFC107)
    )

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .aspectRatio(1f)
            .shadow(10.dp)
            .background(backgroundColor),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = if (item.isPurchased) listOf(Color.LightGray, Color.DarkGray) else listOf(
                            Color(0xFF7E57C2),
                            Color(0xFF512DA8)
                        )
                    )
                )
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Placeholder for avatar image
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        color = Color.Yellow,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "A", // Placeholder, replace with image
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = item.name, fontSize = 16.sp, color = Color.White)

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = "${item.price} coins", fontSize = 14.sp, color = Color.White)

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    isPressed = !isPressed
                    onPurchase(item)
                },
                enabled = !item.isPurchased,
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                modifier = Modifier
                    .padding(4.dp)
                    .shadow(4.dp)
                    .background(buttonColor)
                    .scale(if (isPressed) 0.95f else 1f) // Scale effect on press
            ) {
                Text(text = if (item.isPurchased) "Purchased" else "Buy", color = Color.Black)
            }
        }
    }
}
