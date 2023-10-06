package com.kimi.qrisnew.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kimi.qrisnew.R


@Composable
fun LoginScreen(navController: NavController) {
    DesignBox()
}

@Preview(showBackground = true)
@Composable
fun DesignBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .offset(y = (-330).dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.round_circle_24),
            contentDescription = "background",
            colorFilter = ColorFilter.tint(Color(0xFF29A2E2)),
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .width(600.dp)
                .height(600.dp)
                .align(Alignment.TopCenter)
        )
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .wrapContentSize(Alignment.Center)
            .offset(y = (-50).dp),
        shape = RoundedCornerShape(
            topStart = 30.dp,
            topEnd = 30.dp,
            bottomStart = 30.dp,
            bottomEnd = 30.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
    ) {
        Text(
            text = "Login",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 50.dp),
            color = Color.Black,
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(
                    align = Alignment.CenterVertically
                )
                .padding(top = 50.dp, bottom = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(
                        align = Alignment.CenterVertically
                    )
                    .padding(top = 50.dp, bottom = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                TextFieldLogin(
                    modifier = Modifier
                        .height(56.dp),
                    label = "Username",
                    TextFieldValue = ""
                )
                Spacer(Modifier.height(30.0.dp))
                TextFieldLogin(
                    modifier = Modifier
                        .height(56.dp),
                    label = "Password",
                    TextFieldValue = ""
                )
            }
            Button(modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color(0xFF0787FF),
                    shape = RoundedCornerShape(50)
                )
                .width(200.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black,
                ),
                onClick = { /*TODO*/ }) {
                Text(text = "Login")
            }
            Spacer(Modifier.height(10.0.dp))
            Button(modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color(0xFF0787FF),
                    shape = RoundedCornerShape(50)
                )
                .width(200.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black,
                ),
                onClick = { /*TODO*/ }) {
                Text(text = "Register")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldLogin(
    label: String,
    TextFieldValue: String,
    modifier: Modifier = Modifier
) {
    TextField(
        value = TextFieldValue,
        onValueChange = { TextFieldValue },
        label = { Text(label) },
        modifier = Modifier,
        colors = TextFieldDefaults
            .textFieldColors(
                textColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Black,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                cursorColor = Color.Black,
                placeholderColor = Color.Black,
                disabledTextColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                errorLabelColor = Color.Black,
                containerColor = Color.Transparent,
            ),
    )
}