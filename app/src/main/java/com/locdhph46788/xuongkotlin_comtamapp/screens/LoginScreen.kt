package com.locdhph46788.xuongkotlin_comtamapp.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.locdhph46788.xuongkotlin_comtamapp.R
import com.locdhph46788.xuongkotlin_comtamapp.navigations.ROUTE_MAIN_NAV
import com.locdhph46788.xuongkotlin_comtamapp.viewmodels.AuthViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: AuthViewModel) {
    val context = LocalContext.current
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff252121)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Đăng nhập",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .size(200.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))
            CustomTextField(
                label = "Email",
                value = email.value,
                onValueChange = { email.value = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                label = "Mật khẩu",
                value = password.value,
                onValueChange = { password.value = it }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    if (email.value.isEmpty() || !isValidEmail(email.value)) {
                        Toast.makeText(
                            context,
                            "Vui lòng nhập email đúng định dạng!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (password.value.isEmpty() || password.value.length < 6) {
                        Toast.makeText(
                            context,
                            "Vui lòng nhập mật khẩu có tối thiểu 6 kí tự!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    } else {
                        viewModel.login(email.value, password.value, context, navController)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF5722)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
            ) {
                Text(text = "Đăng nhập", color = Color.White)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    navController.navigate(ROUTE_MAIN_NAV.SIGNUP.name)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00BCD4)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
            ) {
                Text(text = "Đăng ký", color = Color.White)
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(label: String, value: String, onValueChange: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color(0xFFE0E0E0),
                cursorColor = Color.Black,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Gray
            )
        )
    }
}
