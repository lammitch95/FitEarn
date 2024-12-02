package com.example.fitearn.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitearn.R
import com.example.fitearn.auth.Registration
import com.example.fitearn.data.database.AppDatabase
import com.example.fitearn.ui.theme.FitEarnTheme
import com.example.fitearn.utils.ValidationUtils
import kotlinx.coroutines.launch

@Composable
fun RegistrationPage(navController: NavController) {

    val context = LocalContext.current
    val appDatabase = remember { AppDatabase.getDatabase(context) }
    val registrationViewModel: RegistrationScreenViewModel = viewModel(
        factory = RegistrationScreenViewModel.provideFactory(appDatabase)
    )

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

        Spacer(modifier = Modifier.padding(vertical = 30.dp))

        TextField(
            value = registrationViewModel.firstNameState.value,
            onValueChange = { registrationViewModel.onFirstNameChange(it) },
            textStyle = TextStyle(
                color = if (registrationViewModel.firstNameState.value.isEmpty()) Color.White else Color.Green,
                fontSize = 20.sp
            ),
            placeholder = { Text("First Name", color = Color.White, fontSize = 20.sp) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = if (registrationViewModel.firstNameState.value.isEmpty()) Color.White else Color.Green,
                unfocusedIndicatorColor = if (registrationViewModel.firstNameState.value.isEmpty()) Color.White else Color.Green
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = if (registrationViewModel.firstNameState.value.isEmpty()) R.drawable.baseline_error_24 else R.drawable.baseline_check_circle_24),
                    contentDescription = "status Icon",
                    tint = if (registrationViewModel.firstNameState.value.isEmpty()) Color.Red else Color.Green
                )
            },
        )

        Spacer(modifier = Modifier.padding(5.dp))

        TextField(
            value = registrationViewModel.lastNameState.value,
            onValueChange = { registrationViewModel.onLastNameChange(it) },
            textStyle = TextStyle(
                color = if (registrationViewModel.lastNameState.value.isEmpty()) Color.White else Color.Green,
                fontSize = 20.sp
            ),
            placeholder = { Text("Last Name", color = Color.White, fontSize = 20.sp) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = if (registrationViewModel.lastNameState.value.isEmpty()) Color.White else Color.Green,
                unfocusedIndicatorColor = if (registrationViewModel.lastNameState.value.isEmpty()) Color.White else Color.Green
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = if (registrationViewModel.lastNameState.value.isEmpty()) R.drawable.baseline_error_24 else R.drawable.baseline_check_circle_24),
                    contentDescription = "status Icon",
                    tint = if (registrationViewModel.lastNameState.value.isEmpty()) Color.Red else Color.Green
                )
            },
        )

        Spacer(modifier = Modifier.padding(5.dp))

        TextField(
            value = registrationViewModel.emailState.value,
            onValueChange = { registrationViewModel.onEmailChange(it)},
            textStyle = TextStyle(
                color = if (registrationViewModel.emailError.isNotEmpty()) Color.White else Color.Green,
                fontSize = 20.sp
            ),
            placeholder = { Text("Email", color = Color.White, fontSize = 20.sp) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = if (registrationViewModel.emailError.isNotEmpty()) Color.White else Color.Green,
                unfocusedIndicatorColor = if (registrationViewModel.emailError.isNotEmpty()) Color.White else Color.Green
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = if (registrationViewModel.emailError.isNotEmpty()) R.drawable.baseline_error_24 else R.drawable.baseline_check_circle_24),
                    contentDescription = "status Icon",
                    tint = if (registrationViewModel.emailError.isNotEmpty()) Color.Red else Color.Green
                )
            },
        )


        Spacer(modifier = Modifier.padding(5.dp))

        TextField(
            value = registrationViewModel.passwordState.value,
            onValueChange = { registrationViewModel.onPasswordChange(it) },
            textStyle = TextStyle(
                color = if (registrationViewModel.passwordError.isNotEmpty()) Color.White else Color.Green,
                fontSize = 20.sp
            ),
            placeholder = { Text("Password", color = Color.White, fontSize = 20.sp) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = if (registrationViewModel.passwordError.isNotEmpty()) Color.White else Color.Green,
                unfocusedIndicatorColor = if (registrationViewModel.passwordError.isNotEmpty()) Color.White else Color.Green
            ),
            trailingIcon = {
                
                Row(
                    modifier = Modifier.padding(end = 12.dp),

                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically

                ){
                    IconButton(
                        onClick = { registrationViewModel.onPasswordVisibility() },
                    ) {
                        Icon(
                            painter = painterResource(id = if (registrationViewModel.passwordVisiblity.value) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24),
                            contentDescription = if (registrationViewModel.passwordVisiblity.value) "Show Password" else "Hide Password",
                            tint = Color.White
                        )
                    }

                    Icon(
                        painter = painterResource(id = if (registrationViewModel.passwordError.isNotEmpty()) R.drawable.baseline_error_24 else R.drawable.baseline_check_circle_24),
                        contentDescription = "status Icon",
                        tint = if (registrationViewModel.passwordError.isNotEmpty()) Color.Red else Color.Green
                    )
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            visualTransformation = if (registrationViewModel.passwordVisiblity.value) VisualTransformation.None else PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.padding(5.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = registrationViewModel.termCondition.value,
                onCheckedChange = { registrationViewModel.onTermConditionChange(it) },
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
                if (registrationViewModel.registrationError.value.isEmpty() && registrationViewModel.lastNameError.isEmpty() &&
                    registrationViewModel.emailError.isEmpty() && registrationViewModel.passwordError.isEmpty() && registrationViewModel.termCondition.value
                ) {
                    registrationViewModel.onIsLoadingChange(true)

                    registrationViewModel.registerUser{
                        navController.navigate("login")
                    }
                } else {
                    registrationViewModel.onRegistrationError("Registration failed. Please try again.")
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier.size(150.dp, 50.dp)
        ) {
            if (registrationViewModel.isLoading.value) {
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

        Spacer(modifier = Modifier.padding(vertical = 5.dp))

        if (registrationViewModel.registrationError.value.isNotEmpty()) {
            Surface(
                modifier = Modifier.padding(20.dp),
                color = Color.Red,
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(
                    text = registrationViewModel.registrationError.value,
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(15.dp),

                )
            }

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
