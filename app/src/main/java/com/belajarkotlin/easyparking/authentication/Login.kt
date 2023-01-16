package com.belajarkotlin.easyparking.authentication

import android.os.Handler
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.belajarkotlin.classreminder.doubleClickHandler.clickableSingle
import com.belajarkotlin.easyparking.R
import com.belajarkotlin.easyparking.component.CButton
import com.belajarkotlin.easyparking.component.CTextField
import com.belajarkotlin.easyparking.component.TextFieldState
import com.belajarkotlin.easyparking.function.openWA
import com.belajarkotlin.easyparking.navigation.Screen
import com.belajarkotlin.easyparking.navigation.popUpToTop
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun Login(navHostController: NavHostController) {
    val systemUiController: SystemUiController = rememberSystemUiController()
    val context = LocalContext.current

    //Set Status Bar to Transparent
    if (systemUiController.isStatusBarVisible) {
        systemUiController.setStatusBarColor(Color(0xFFE9ECF4), darkIcons = true)
    }
    val loginButtonState = rememberSaveable {
        mutableStateOf(true)
    }
    val emailState = remember {
        TextFieldState()
    }
    val passwordState = remember {
        TextFieldState()
    }

    val focusManager = LocalFocusManager.current

    val Lexend = FontFamily(
        Font(R.font.lexend_light, FontWeight.Light),
        Font(R.font.lexend_regular, FontWeight.Normal),
        Font(R.font.lexend_bold, FontWeight.Bold)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE9ECF4)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Top Image
        Image(
            painter = painterResource(
                id = R.drawable.logo
            ), contentDescription = null, modifier = Modifier
                .height(120.dp)
                .width(250.dp)
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        //Subject Text
        Row() {
            Text(
                fontFamily = Lexend,
                fontWeight = FontWeight.Normal,
                text = "Masuk ke ",
                fontSize = 20.sp
            )
            Text(
                text = "Easy Parking",
                fontFamily = Lexend,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        //Text Field Email
        CTextField(
            state = emailState, label = "Email", keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ), isPassword = false, modifier = Modifier.padding(horizontal = 30.dp)
        )
        CTextField(
            state = passwordState, label = "Kata Sandi", keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ), isPassword = true, modifier = Modifier.padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.padding(vertical = 0.dp))
        //Already Have Account Text
        Row(
            horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Belum Punya Akun?",
                fontFamily = Lexend,
                fontWeight = FontWeight.Light,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            Text(text = "Daftar",
                fontFamily = Lexend,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                modifier = Modifier.clickable(true) {
                    navHostController.navigate(route = Screen.Register.route) {
                        launchSingleTop = true
                        popUpToTop(navHostController)
                    }
                })
        }
        Spacer(modifier = Modifier.padding(vertical = 30.dp))

        //Register Button
        CButton(
            text = "Masuk",
            color = Color(0xFFFF9900),
            enabledState = loginButtonState.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            focusManager.clearFocus()
            loginButtonState.value = false
            when {
                (emailState.text.isEmpty()) -> {
                    Toast.makeText(context, "Silahkan masukan email anda", Toast.LENGTH_SHORT)
                        .show()
                    Handler().postDelayed({
                        loginButtonState.value = true
                    }, 2000)
                }

                (passwordState.text.isEmpty()) -> {
                    Toast.makeText(context, "Silahkan masukan password anda", Toast.LENGTH_SHORT)
                        .show()
                    Handler().postDelayed({
                        loginButtonState.value = true
                    }, 2000)
                }

                else -> {
                    //TODO
                }
            }

        }
        Spacer(modifier = Modifier.padding(vertical = 50.dp))
        //Need Help Support
        Text(
            text = "Butuh Bantuan?",
            fontFamily = Lexend,
            fontWeight = FontWeight.Light,
            fontSize = 14.sp
        )
        Text(
            text = "Hubungi Customer Service",
            fontFamily = Lexend,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            modifier = Modifier.clickableSingle {
                openWA(context)
            }
        )
    }
}