package com.example.fitearn.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitearn.ui.theme.FitEarnTheme
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import com.example.fitearn.auth.UserDataManager
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UserInfoScreen(navController: NavController) {

    //Variables
    var dateOfBirth by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }


    var dateOfBirthError by remember { mutableStateOf("") }
    var weightError by remember { mutableStateOf("") }
    var heightError by remember { mutableStateOf("") }
    var phoneNumberError by remember { mutableStateOf("") }

    val context = LocalContext.current
    val UserInfoScreenViewModel: UserInfoScreenViewModel = viewModel(
        factory = UserInfoScreenViewModel.provideFactory()
    )


    //Arranges children vertically
    Column(
        modifier = Modifier
            .fillMaxSize()

            //Background color of the screen, goes from pink to blue
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(201, 1, 254),
                        Color(0, 76, 249)
                    )
                )
            )
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        //Creates space betweent the title and text
        Spacer(modifier = Modifier.padding(vertical = 80.dp))

        //User Info Text Title
        Text(
            text = "User Info",
            color = Color(255, 152, 0, 255),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 20.dp)
        )

        //Creates space between the text and the text field
        Spacer(modifier = Modifier.padding(vertical = 10.dp))


        //Date Of Birth Textfield ********************************************************************
        TextField(
            value = dateOfBirth,

            //Only accepts numbers and updates the textfield accordingly, digits only
            //Trying to make this where user must only put 8 digits
            onValueChange = { if (it.length <= 8) { dateOfBirth = it.filter { it.isDigit() } } },

            //Shows user a keyboard of digits to prevent user from inputting letters
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(color = Color.White, fontSize = 20.sp),

            //Hint of textfield
            placeholder = { Text("Date of Birth (MM/DD/YYYY)", color = Color.White, fontSize = 20.sp) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color(255, 152, 0, 255),
                unfocusedIndicatorColor = Color.White
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
        )

        //Shows error message underneath date of birth text field
        if (dateOfBirthError.isNotEmpty()) {
            Text(
                text = dateOfBirthError,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 4.dp)
            )
        }

        //Creates space between textfields
        Spacer(modifier = Modifier.padding(10.dp))

        //Weight Textfield ********************************************************************
        TextField(
            value = weight,
            onValueChange = { if (it.length <= 3) { weight = it.filter { it.isDigit() } } },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(color = Color.White, fontSize = 20.sp),
            placeholder = { Text("Weight", color = Color.White, fontSize = 20.sp) },
            trailingIcon = {
                Text("lb", color = Color.White, fontSize = 20.sp)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color(255, 152, 0, 255),
                unfocusedIndicatorColor = Color.White
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
        )

        //Shows error message underneath weight field
        if (weightError.isNotEmpty()) {
            Text(
                text = weightError,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.padding(10.dp))

        //Height Textfield ********************************************************************
        TextField(
            value = if (height.length > 1 ){ "${height[0]}'${height.substring(1)}" } else { height },
            onValueChange = { if (it.length <= 2) { height = it.filter { it.isDigit() } } },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(color = Color.White, fontSize = 20.sp),
            placeholder = { Text("Height", color = Color.White, fontSize = 20.sp) },
            trailingIcon = {
                Text("ft", color = Color.White, fontSize = 20.sp)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color(255, 152, 0, 255),
                unfocusedIndicatorColor = Color.White
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
        )

        //Shows error message underneath height field
        if (heightError.isNotEmpty()) {
            Text(
                text = heightError,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.padding(10.dp))

        //Phone Number Textfield ********************************************************************
        TextField(
            value = phoneNumber,
            onValueChange = { if (it.length <= 10) { phoneNumber = it.filter { it.isDigit() } } },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(color = Color.White, fontSize = 20.sp),
            placeholder = { Text("Phone Number", color = Color.White, fontSize = 20.sp) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color(255, 152, 0, 255),
                unfocusedIndicatorColor = Color.White
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
        )

        //Shows error message underneath phone number field
        if (phoneNumberError.isNotEmpty()) {
            Text(
                text = phoneNumberError,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.padding(30.dp))

        //"Lets Get Started" Button
        Button(
            onClick = {

                //Once user clicks, it will show a message beneath the textfield that is empty indicating it must be filled out

                dateOfBirthError = if (dateOfBirth.isEmpty()) "Date Of Birth Field Cannot Be Empty!" else ""
                weightError = if (weight.isEmpty()) "Weight Field Cannot Be Empty!" else ""
                heightError = if (height.isEmpty()) "Height Field Cannot Be Empty!" else ""
                phoneNumberError = if (phoneNumber.isEmpty()) "Phone Number Field Cannot Be Empty!" else ""

                // If all fields are filled correctly, it will navigate to the user profile screen. Otherwise, it will show a toast message indicating that some fields are empty.
                if (dateOfBirthError.isEmpty() && weightError.isEmpty() && heightError.isEmpty() && phoneNumberError.isEmpty()) {

                    UserDataManager.phoneNumber = phoneNumber
                    UserDataManager.dateOfBirth = dateOfBirth
                    UserDataManager.weight = weight

                    Toast.makeText(context,"All fields are filled correctly. Proceeding...", Toast.LENGTH_SHORT).show()

                    // Navigate to the user profile screen after successful registration
                    navController.navigate("userprofile")
                } else {
                    Toast.makeText(context,"Must Fill Out Empty Fields! Try again.", Toast.LENGTH_SHORT).show()
            }

            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier.size(200.dp, 50.dp)
        ) {
            Text(
                text = "Let's Get Started",
                color = Color(0, 76, 249),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewUserInfoScreen() {
    FitEarnTheme {
        UserInfoScreen(navController = rememberNavController())
    }
}
