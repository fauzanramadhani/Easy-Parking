package com.belajarkotlin.easyparking.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.belajarkotlin.easyparking.R

class TextFieldState {
    var text: String by mutableStateOf("")
}

@Composable
fun CTextField(
    modifier: Modifier = Modifier,
    state: TextFieldState = remember { TextFieldState() },
    label: String,
    keyboardOptions: KeyboardOptions,
    isPassword: Boolean,
    length: Int = 50,
    backgroundColor: Color = Color.White,
    singleLine: Boolean = true,
    readyOnly: Boolean = false,
) {
    val focusManager = LocalFocusManager.current
    if (isPassword) {
        var visible = remember {
            mutableStateOf(false)
        }
        val image = if (visible.value) {
            painterResource(id = R.drawable.ic_eye_visibility_off)
        } else {
            painterResource(id = R.drawable.ic_eye_visible)
        }
        var passwordVisibility: Boolean by remember { mutableStateOf(false) }
        TextField(
            readOnly = readyOnly,
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            label = {
                Text(text = label)
            },
            value = state.text,
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = backgroundColor,
                cursorColor = Color.Black,
                disabledLabelColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                focusedLabelColor = Color.Black,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                if (it.length <= length) state.text = it
            },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            trailingIcon =
            {
                Icon(
                    painter = image,
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            visible.value = !visible.value
                            passwordVisibility = !passwordVisibility
                            Log.e("Visible", visible.toString())
                        },
                )

            }
        )
    } else {
        TextField(
            readOnly = readyOnly,
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            label = {
                Text(text = label)
            },
            value = state.text,
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = keyboardOptions,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = backgroundColor,
                cursorColor = Color.Black,
                disabledLabelColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                focusedLabelColor = Color.Black,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                if (it.length <= length) state.text = it
            },
            shape = RoundedCornerShape(8.dp),
            singleLine = singleLine,
            trailingIcon = {
                if (state.text.isNotEmpty()) {
                    if (!readyOnly) {
                        IconButton(onClick = { state.text = "" }) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        )
    }
}