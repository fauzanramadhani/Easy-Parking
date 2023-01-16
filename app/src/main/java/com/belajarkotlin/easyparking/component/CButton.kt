package com.belajarkotlin.easyparking.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.belajarkotlin.easyparking.R

@Composable
fun CButton(
    text: String,
    color: Color,
    textColor: Color = Color.White,
    modifier: Modifier = Modifier,
    enabledState: Boolean = true,
    onClickPressed: () -> Unit = {},
) {
    val Lexend = FontFamily(
        Font(R.font.lexend_regular, FontWeight.Normal),
        Font(R.font.lexend_light, FontWeight.Light),
        Font(R.font.lexend_bold, FontWeight.Bold),
    )
    Button(
        onClick = onClickPressed,
        modifier = modifier
            .height(40.dp)
            .clip(RoundedCornerShape(2.dp)),
        colors = ButtonDefaults.buttonColors(backgroundColor = color),
        enabled = enabledState
    ) {
        Text(
            text = text,
            color = textColor,
            fontFamily = Lexend,
            fontWeight = FontWeight.Normal
        )
    }
}