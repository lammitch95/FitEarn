package com.example.fitearn.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitearn.R
import com.example.fitearn.auth.Registration
import com.example.fitearn.ui.theme.FitEarnTheme
import com.example.fitearn.utils.ValidationUtils
import kotlinx.coroutines.launch

@Composable
fun RegistrationPage(navController: NavController) {
    var firstNameState by remember { mutableStateOf("") }
    var lastNameState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }
    var termCondition by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var registrationError by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    // Validation error messages
    val firstNameError = if (firstNameState.isEmpty()) "First name cannot be empty" else ""
    val lastNameError = if (lastNameState.isEmpty()) "Last name cannot be empty" else ""
    val emailError = if (emailState.isEmpty()) "Email cannot be empty" else ValidationUtils.validateEmail(emailState)
    val passwordError = if (passwordState.isEmpty()) "Password cannot be empty" else ValidationUtils.validatePassword(passwordState)

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
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.padding(vertical = 100.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 40.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Sign up",
                color = Color(255, 152, 0, 255),
                fontSize = 20.sp,
                modifier = Modifier.padding(end = 10.dp)
            )

            Text(
                text = "Sign in",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier.clickable {
                    navController.navigate("login")
                }
            )
        }

        Spacer(modifier = Modifier.padding(vertical = 50.dp))

        TextField(
            value = firstNameState,
            onValueChange = { firstNameState = it },
            textStyle = TextStyle(color = Color.White, fontSize = 20.sp),
            placeholder = { Text("First Name", color = Color.White, fontSize = 20.sp) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color(255, 152, 0, 255),
                unfocusedIndicatorColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp)
        )
        if (firstNameError.isNotEmpty()) {
            Text(text = firstNameError, color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.padding(5.dp))

        TextField(
            value = lastNameState,
            onValueChange = { lastNameState = it },
            textStyle = TextStyle(color = Color.White, fontSize = 20.sp),
            placeholder = { Text("Last Name", color = Color.White, fontSize = 20.sp) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color(255, 152, 0, 255),
                unfocusedIndicatorColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp)
        )
        if (lastNameError.isNotEmpty()) {
            Text(text = lastNameError, color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.padding(5.dp))

        TextField(
            value = emailState,
            onValueChange = { emailState = it },
            textStyle = TextStyle(color = Color.White, fontSize = 20.sp),
            placeholder = { Text("Email", color = Color.White, fontSize = 20.sp) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color(255, 152, 0, 255),
                unfocusedIndicatorColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp)
        )
        if (emailError.isNotEmpty()) {
            Text(text = emailError, color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.padding(5.dp))

        TextField(
            value = passwordState,
            onValueChange = { passwordState = it },
            textStyle = TextStyle(color = Color.White, fontSize = 20.sp),
            placeholder = { Text("Password", color = Color.White, fontSize = 20.sp) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color(255, 152, 0, 255),
                unfocusedIndicatorColor = Color.White
            ),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = painterResource(id = if (passwordVisible) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24),
                        contentDescription = if (passwordVisible) "Show Password" else "Hide Password",
                        tint = Color.White
                    )
                }
            },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )
        if (passwordError.isNotEmpty()) {
            Text(text = passwordError, color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.padding(5.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = termCondition,
                onCheckedChange = { termCondition = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.White,
                    uncheckedColor = Color.White,
                    checkmarkColor = Color.White
                ),

            )
            Text(
                text = "Agree with Terms & Conditions",
                color = Color.White,
                fontSize = 17.sp
            )
        }

        Spacer(modifier = Modifier.padding(15.dp))

        Button(
            onClick = {
                // Check all validation and the checkbox condition
                if (firstNameError.isEmpty() && lastNameError.isEmpty() && emailError.isEmpty() &&
                    passwordError.isEmpty() && termCondition
                ) {
                    isLoading = true
                    registrationError = ""
                    coroutineScope.launch {
                        val success = Registration.registerUser(
                            firstName = firstNameState,
                            lastName = lastNameState,
                            email = emailState,
                            password = passwordState
                        )
                        isLoading = false
                        if (success) {
                            navController.popBackStack() // Navigate back to login screen on successful registration
                        } else {
                            registrationError = "Registration failed. Please try again."
                        }
                    }
                } else {
                    registrationError = "Please fill all fields correctly and accept the Terms & Conditions."
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier.size(150.dp, 50.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color(0, 76, 249), modifier = Modifier.size(20.dp))
            } else {
                Text(
                    text = "Submit",
                    color = Color(0, 76, 249),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        if (registrationError.isNotEmpty()) {
            Text(
                text = registrationError,
                color = Color.Red,
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegistrationPage() {
    FitEarnTheme {
        RegistrationPage(navController = rememberNavController())
    }
}
