package com.example.fitearn.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitearn.R
import com.example.fitearn.model.ShopItem
import com.example.fitearn.model.ShopItemsRepository
import com.example.fitearn.ui.theme.FitEarnTheme
import com.example.fitearn.utils.LoggedUser

@Composable
fun AvatarCollectionScreen(navController: NavController) {

    val avatarCollectViewModel: AvatarCollectionViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(201, 1, 254),
                        Color(0, 76, 249)
                    )
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            IconButton(
                onClick = { navController.navigate("dashboard") },
                modifier = Modifier
                    .size(60.dp)
                    .padding(start = 4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.home_button),
                    contentDescription = "Home Icon",
                    modifier = Modifier.size(60.dp),
                    tint = Color.Unspecified
                )
            }
        }

        Spacer(modifier = Modifier.padding(vertical = 12.dp))

        Text(
            "Avatars",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(252, 181, 115, 255),
                            Color(255, 123, 0)
                        )
                    ),
                    shape = RoundedCornerShape(25.dp)
                )
                .padding(10.dp, 10.dp)
                .weight(1f),
            style = TextStyle(
                shadow = Shadow(
                    color = Color(105, 63, 0, 255),
                    offset = Offset(6f, 4f),
                    blurRadius = 0f
                )
            )

        )

        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(13f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            UserAvatarCollection(avatarCollectViewModel)
        }
    }
}



@Composable
fun UserAvatarCollection(viewModel: AvatarCollectionViewModel){

    val items = LoggedUser.loggedInUser?.ownAvatars
    val maxRows = 2
    val maxColumns = 3

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (rowIndex in 0 until maxRows) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (colIndex in 0 until maxColumns) {
                    val itemIndex = rowIndex * maxColumns + colIndex
                    if (items != null) {
                        if (itemIndex < items.size) {
                            CollectionItemTemplate(items[itemIndex], viewModel)
                        } else {
                            Spacer(modifier = Modifier.size(100.dp, 125.dp))
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun CollectionItemTemplate(item: ShopItem, viewModel: AvatarCollectionViewModel){

    val isEquipped = item.name == viewModel.selectedAvatar.value
    Column(
        modifier = Modifier
            .clickable{
                viewModel.onEquip(item)
            }
            .padding(4.dp)
            .size(100.dp, 125.dp)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(252, 181, 115, 255),
                        Color(255, 123, 0)
                    )
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 5.dp,
                color = if(isEquipped) Color.Green else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom

    ) {

        Icon(
            painter = painterResource(id = item.imageResId),
            contentDescription = "Home Icon",
            modifier = Modifier
                .size(65.dp)
                .weight(4f),
            tint = Color.Unspecified
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.5f)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(255, 106, 0, 255),
                            Color(221, 92, 0, 255)
                        )
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(3.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Text(
                item.name,
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAvatarCollectionScreen() {
    FitEarnTheme {
        AvatarCollectionScreen(navController = rememberNavController())
    }
}