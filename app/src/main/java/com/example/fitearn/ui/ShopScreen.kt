package com.example.fitearn.ui

import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitearn.R
import com.example.fitearn.model.ShopItem
import com.example.fitearn.model.ShopItemsRepository

@Composable
fun ShopScreen(navController: NavController){

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
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){

            IconButton(
                onClick = { navController.navigate("dashboard") },
                modifier = Modifier.size(60.dp)
                    .padding(start = 4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.home_button),
                    contentDescription = "Home Icon",
                    modifier = Modifier.size(60.dp),
                    tint = Color.Unspecified
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ){
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ){

                    Icon(
                        painter = painterResource(id = R.drawable.coins_icon),
                        contentDescription = "Coin Icon",
                        modifier = Modifier
                            .size(25.dp),
                        tint = Color.White
                    )

                    Spacer(modifier = Modifier.size(7.dp))

                    Text(
                        "100.00k",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                }

                Spacer(modifier = Modifier.padding(horizontal = 10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.dollar_sign_two),
                        contentDescription = "Dollar Icon",
                        modifier = Modifier
                            .size(25.dp),
                        tint = Color.White
                    )

                    Spacer(modifier = Modifier.size(5.dp))

                    Text(
                        "100.00k",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                }
            }

            

        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ){
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
                        .padding(10.dp,10.dp)
                        .weight(1f),
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color(105, 63, 0, 255),
                            offset = Offset(6f, 4f),
                            blurRadius = 0f
                        )
                    )

                )
                
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(6f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ){
                    AvatarItem()
                }

                Spacer(modifier = Modifier.padding(vertical = 5.dp))

                Text(
                    "Rewards",
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
                        .padding(20.dp,10.dp)
                        .weight(1f),
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color(105, 63, 0, 255),
                            offset = Offset(6f, 4f),
                            blurRadius = 0f
                        )
                    )

                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(6f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ){
                    RedeemItem()
                }



            }
        }
    }

}


@Composable
fun AvatarItem(){

    val items = ShopItemsRepository.avatarItems
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
                    if (itemIndex < items.size) {

                        ItemTemplate(item = items[itemIndex])
                    } else {
                        Spacer(modifier = Modifier.size(100.dp, 125.dp))
                    }
                }
            }
        }

    }
}

@Composable
fun RedeemItem(){

    val items = ShopItemsRepository.redeemItems
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
                    if (itemIndex < items.size) {

                        ItemTemplate(item = items[itemIndex])
                    } else {
                        Spacer(modifier = Modifier.size(100.dp, 125.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ItemTemplate(item: ShopItem){
    Column(
        modifier = Modifier
            .padding(4.dp)
            .size(100.dp,125.dp)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(252, 181, 115, 255),
                        Color(255, 123, 0)
                    )
                ),
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
                .weight(2f)
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
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){

                val iconRes = when (item.itemType) {
                    "Avatar" -> R.drawable.coins_icon
                    "Redeem" -> R.drawable.dollar_sign_two
                    else -> null
                }

                iconRes?.let {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = "Currency Icon",
                        modifier = Modifier
                            .size(13.dp),
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.size(3.dp))

                Text(
                    "${item.cost}",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }

    }
}




@Preview(showBackground = true)
@Composable
fun PreviewShopScreen(){
    ShopScreen(navController = rememberNavController())
}