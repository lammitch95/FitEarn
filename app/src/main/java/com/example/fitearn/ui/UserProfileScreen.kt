package com.example.fitearn.ui

import coil.compose.rememberAsyncImagePainter
import androidx.compose.foundation.clickable
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.background
import androidx.compose.ui.unit.dp
import android.net.Uri
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.fitearn.R
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fitearn.auth.fetchUserFromAuth
import com.example.fitearn.data.database.AppDatabase
import com.example.fitearn.ui.theme.FitEarnTheme
import com.example.fitearn.model.ShopItemsRepository
import com.example.fitearn.utils.LoggedUser


@Composable
fun UserProfile(navController: NavHostController) {
    // Access the logged-in user
    val user = LoggedUser.loggedInUser

    // Error handling if the user is null
    if (user == null) {
        Text("User not logged in", color = Color.Red, modifier = Modifier.padding(16.dp))
        return
    }

//        val viewModel: UserProfileScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
//            factory = UserProfileScreenViewModel.provideFactory(appDatabase)
//        )
//
//        val UserData by viewModel.UserData.collectAsState()
//
//    var firstName by remember { mutableStateOf("") }
//    var lastName by remember { mutableStateOf("") }
//    var phoneNumber by remember { mutableStateOf("") }
//    var email by remember { mutableStateOf("") }
//    var weight by remember { mutableStateOf("") }
//    var dateOfBirth by remember { mutableStateOf("") }
//
//    val context = LocalContext.current
//
//
//    /*
//    val appDatabase = remember { AppDatabase.getDatabase(context) }
//    val UserProfileScreenViewModel: UserProfileScreenViewModel = viewModel(
//        factory = UserProfileScreenViewModel.provideFactory(appDatabase)
//    )*/
//
//    // Fetch user data
//    LaunchedEffect(Unit) {
//        fetchUserFromAuth(
//            onSuccess = { user ->
//                firstName = user.firstName
//                lastName = user.lastName
//                phoneNumber = user.phoneNumber
//                email = user.email
//                weight = user.weight
//                dateOfBirth = user.dateOfBirth
//            },
//            onError = { e ->
//                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
//            }
//        )
//    }

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
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
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

            Spacer(modifier = Modifier.weight(0.2f))

            UserAvatarSelect(
                modifier = Modifier.size(250.dp),
                defaultImage = R.drawable.user_pfp_account,
                navController
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
                        value = "Name: ${user.firstName} ${user.lastName}",
                        onValueChange = {  },
                        readOnly = true,
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

                // Mobile Row ******************************************************************************************
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
                        value = "Mobile: ${user.phoneNum ?: "Not provided"}",
                        onValueChange = {  },
                        readOnly = true,
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

                // Email Row ******************************************************************************************
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
                        value =  "Email: ${user.email}",
                        onValueChange = {  },
                        readOnly = true,
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

                // Weight Row ******************************************************************************************
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
                        value = "Weight: ${user.weight ?: "Not provided"}",
                        onValueChange = {  },
                        readOnly = true,
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

                // DOB Row ******************************************************************************************
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
                            .padding(start = 4.dp),
                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color.Black)
                    )
                    TextField(
                        value = "D.O.B: ${user.dateOfBirth ?: "Not provided"}",
                        onValueChange = {  },
                        readOnly = true,
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
                        .height(55.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                    )
                ) {
                    Text(
                        text = "Sign Out",
                        color = Color(0, 76, 249),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun UserAvatarSelect(modifier: Modifier = Modifier, defaultImage: Int, navController: NavHostController){

    val avatarItems = ShopItemsRepository.avatarItems
    val userEquippedAvatar = LoggedUser.loggedInUser?.equippedAvatar
    var chosenImage = defaultImage
    for (item in avatarItems) {
        if (item.name == userEquippedAvatar) {
            chosenImage = item.imageResId
            break
        }
    }

    Box(
        modifier = modifier
            .size(150.dp)
            .padding(8.dp)
            .clip(CircleShape)
            .clickable { navController.navigate("avatarCollection") },
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = chosenImage),
            contentDescription = "Default Profile Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}


@Composable
fun UserImagePicker(modifier: Modifier = Modifier, defaultImage: Int) {
    val imageUri = remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> if (uri != null) imageUri.value = uri }
    )

    Box(
        modifier = modifier
            .size(150.dp)
            .padding(8.dp)
            .clip(CircleShape)
            .clickable { launcher.launch("image/*") },
        contentAlignment = Alignment.Center
    ) {
        if (imageUri.value != null) {
            Image(
                painter = rememberAsyncImagePainter(model = imageUri.value),
                contentDescription = "Selected Profile Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                painter = painterResource(id = defaultImage),
                contentDescription = "Default Profile Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
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