package com.example.fitearn.ui

import com.example.fitearn.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fitearn.R
import com.example.fitearn.ui.theme.FitEarnTheme


@Composable
fun UserProfile(navController: NavHostController) {
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
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.navigate("dashboard") },
                modifier = Modifier.size(60.dp)
                    .align(Alignment.Top)
                    .padding(top = 10.dp)
                    .padding(start = 4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.home_button),
                    contentDescription = "Home Icon",
                    modifier = Modifier.size(60.dp),
                    tint = Color.Unspecified
                )
            }

            Spacer(modifier = Modifier.weight(0.2f))

            //Functionality:
            Image(
                painter = painterResource(id = R.drawable.user_pfp_account),
                contentDescription = "User Profile Picture",
                modifier = Modifier.size(250.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

        }


        Image(
            painter = painterResource(id = R.drawable.account_information),
            contentDescription = "Account Information",
            modifier = Modifier
                .fillMaxWidth()
                .height(62.dp)
                .padding(top = 10.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(550.dp)
                .padding(top = 10.dp)

        ) {
            Image(
                painter = painterResource(id = R.drawable.backboard),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(400.dp)
                    .align(Alignment.Center)
                    .padding(bottom = 20.dp),
                contentScale = ContentScale.FillBounds
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 16.dp)
            ) {
                // Name Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.person),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(start = 4.dp)
                    )
                    TextField(
                        value = "Name:",
                        onValueChange = { /* Handle text change */ },
                        modifier = Modifier
                            .width(340.dp)
                            .height(55.dp)
                            .padding(start = 8.dp),
                        textStyle = TextStyle(color = Color.White, fontWeight = FontWeight.Bold),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = Color.White,
                            focusedIndicatorColor = Color.White,
                            unfocusedIndicatorColor = Color.White
                        )
                    )
                }

                // Mobile Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.mobile_friendly),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(start = 4.dp)
                    )
                    TextField(
                        value = "Mobile:",
                        onValueChange = { /* Handle text change */ },
                        modifier = Modifier
                            .width(340.dp)
                            .height(55.dp)
                            .padding(start = 8.dp),
                        textStyle = TextStyle(color = Color.White, fontWeight = FontWeight.Bold),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = Color.White,
                            focusedIndicatorColor = Color.White,
                            unfocusedIndicatorColor = Color.White
                        )
                    )
                }

                // Email Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.mail),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(start = 4.dp)
                    )
                    TextField(
                        value = "Email:",
                        onValueChange = { /* Handle text change */ },
                        modifier = Modifier
                            .width(340.dp)
                            .height(55.dp)
                            .padding(start = 8.dp),
                        textStyle = TextStyle(color = Color.White, fontWeight = FontWeight.Bold),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = Color.White,
                            focusedIndicatorColor = Color.White,
                            unfocusedIndicatorColor = Color.White
                        )
                    )
                }

                // Address Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.directions_car),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(start = 4.dp)
                    )
                    TextField(
                        value = "Address:",
                        onValueChange = { /* Handle text change */ },
                        modifier = Modifier
                            .width(340.dp)
                            .height(55.dp)
                            .padding(start = 8.dp),
                        textStyle = TextStyle(color = Color.White, fontWeight = FontWeight.Bold),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = Color.White,
                            focusedIndicatorColor = Color.White,
                            unfocusedIndicatorColor = Color.White
                        )
                    )
                }

                // DOB Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = android.R.drawable.ic_menu_my_calendar),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(start = 4.dp)
                    )
                    TextField(
                        value = "D.O.B:",
                        onValueChange = { /* Handle text change */ },
                        modifier = Modifier
                            .width(340.dp)
                            .height(55.dp)
                            .padding(start = 8.dp),
                        textStyle = TextStyle(color = Color.White, fontWeight = FontWeight.Bold),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = Color.White,
                            focusedIndicatorColor = Color.White,
                            unfocusedIndicatorColor = Color.White
                        )
                    )
                }

                Button(
                    onClick = { navController.navigate("login") },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 40.dp)
                        .width(200.dp)
                        .height(55.dp)
                ) {
                    Text(text = "Sign Out")
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewUserProfilePage() {
    FitEarnTheme {
        UserProfile(navController = rememberNavController())
    }
}