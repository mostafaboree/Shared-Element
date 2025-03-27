package com.example.sharedelement.shose_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharedelement.R

@Composable
fun ProductCard() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)

                .background(Color.Black)
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 40.dp, start = 1.dp, end = 30.dp, bottom = 40.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.White,
                                Color.LightGray, Color.Cyan.copy(alpha = 0.2f)
                            )
                        ), shape = RoundedCornerShape(20.dp)
                    )
                    .size(200.dp)
            ) {
                Text(
                    text = "$ 120 ",
                    fontSize = 14.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(10.dp)
                )

                Icon(
                    imageVector = Icons.Sharp.FavoriteBorder,
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(40.dp)
                        .padding(10.dp), tint = Color.White
                )
            }
            Image(
                painter = painterResource(id = R.drawable.h1),
                contentDescription = null,
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .rotate(-30f)
                    .padding(start = 10.dp)
                    .align(Alignment.TopEnd),
                contentScale = ContentScale.Fit,
                alignment = Alignment.TopEnd
            )
            Text(
                text = "Nike Air Max 97",
                fontSize = 20.sp,
                color = Color.LightGray,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(end = 10.dp),
                textAlign = TextAlign.Start
            )

        }
    }
}
@Preview(showBackground = true,showSystemUi = true)
@Composable
fun PreviewProductCard() {
    ProductCard()
}