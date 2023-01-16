package com.belajarkotlin.easyparking.onBoarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.belajarkotlin.easyparking.R
import com.belajarkotlin.easyparking.component.CButton
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoarding(
    navHostController: NavHostController
) {
    val scope = rememberCoroutineScope()
    val systemUiController: SystemUiController = rememberSystemUiController()

    //Set Status Bar to Transparent
    if (systemUiController.isStatusBarVisible) {
        systemUiController.setStatusBarColor(Color.Transparent, darkIcons = true)
    }
    Column(
        modifier = Modifier
            .background(color = Color.White)
    ) {
        val items = OnBoardingItem.get()
        val state = rememberPagerState(pageCount = items.size)
        HorizontalPager(
            state = state,
            modifier = Modifier
                .weight(0.8f)
                .padding(horizontal = 20.dp)
        ) { page ->
            OnBoardingItem(items[page])
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 30.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(vertical = 13.dp)
            ) {
                BottomSection(size = items.size, index = state.currentPage)
            }
            Spacer(modifier = Modifier.weight(1f))
            if (state.currentPage == (items.size - 1)) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    CButton(
                        text = "Daftar",
                        color = Color(0xFFFF9900),
                        modifier = Modifier
                            .width(120.dp),
                        enabledState = true,
                    ) {
//                        navController.navigate(route = Screen.Register.route) {
//                            launchSingleTop = true
//                        }

                    }
                    CButton(
                        text = "Masuk",
                        color = Color(0xFFFF9900),
                        modifier = Modifier
                            .width(120.dp),
                        enabledState = true,
                    ) {
//                        navController.navigate(route = Screen.Login.route) {
//                            launchSingleTop = true
//                        }
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFF9900))
                ) {
                    IconButton(
                        onClick = {
                            scope.launch {
                                state.scrollToPage(state.currentPage + 1)
                            }
                        }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_right),
                            contentDescription = null,
                            modifier = Modifier
                                .size(25.dp)
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun BottomSection(size: Int, index: Int) {
    Box() {
        Indicators(size = size, index = index)
    }
}

@Composable
fun Indicators(size: Int, index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
//            .align(Alignment.Center)
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }
    }
}

@Composable
fun Indicator(
    isSelected: Boolean
) {
    Box(
        modifier = Modifier
            .height(10.dp)
            .width(10.dp)
            .clip(CircleShape)
            .background(
                if (isSelected) {
                    Color(0xFFFF9900)
                } else {
                    Color(0xFFD9D9D9)
                }
            )
    ) {

    }
}

@Composable
fun OnBoardingItem(item: OnBoardingItem) {
    val Lexend = FontFamily(
        Font(R.font.lexend_regular, FontWeight.Normal),
        Font(R.font.lexend_light, FontWeight.Light),
        Font(R.font.lexend_bold, FontWeight.Bold),
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = item.image),
            contentDescription = null,
            modifier = Modifier
                .width(350.dp)
                .height(300.dp)
        )
        Spacer(modifier = Modifier.padding(vertical = 0.dp))
        Text(
            text = stringResource(id = item.title),
            fontSize = 17.sp,
            color = Color(0xFF000000),
            fontFamily = Lexend,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        Text(
            text = stringResource(id = item.text),
            fontSize = 14.sp,
            color = Color(0xFF000000).copy(0.9f),
            textAlign = TextAlign.Center,
            fontFamily = Lexend,
            fontWeight = FontWeight.Light,
            modifier = Modifier
                .padding(horizontal = 15.dp)
        )
    }
}