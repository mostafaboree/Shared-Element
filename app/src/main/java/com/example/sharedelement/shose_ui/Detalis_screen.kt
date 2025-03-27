package com.example.sharedelement.shose_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharedelement.R

typealias  RM = R.drawable
typealias  const = R.string

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailsShoes(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            Toolbar()
            ProductIamge()
            Spacer(modifier = Modifier.height(20.dp))
            RateingBar()
            Text(text = "$120",fontSize = 30.sp,color = Color.hsv(147f, 0.5f, 0.5f), fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive)
            Text(text ="headphone",fontSize = 18.sp,color = Color.LightGray, fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif,
                maxLines = 5,overflow = TextOverflow.Visible,modifier = Modifier.padding(10.dp))

            ItemColor()
            Action()
            Text(text = "$120",fontSize = 30.sp,color = Color.hsv(147f, 0.5f, 0.5f), fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive)

        }

    }

}


@Composable
private fun Toolbar() {
    Row(
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Default.ArrowBack,
            contentDescription ="",modifier = Modifier.size(30.dp) ,tint = Color.White)
        Image(
            painter = painterResource(id = R.drawable.nike),
            contentDescription = "list_icon",
            modifier = Modifier.size(50.dp),
            contentScale = ContentScale.FillBounds
        )
        Image(
            painter = painterResource(id = R.drawable.cart),
            contentDescription = "list_icon", modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
private  fun ProductIamge(){
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .fillMaxHeight(.45f)
            .fillMaxWidth() ){
            Image(painter = painterResource(id = R.drawable.h3), contentDescription = null,
                modifier = Modifier
                    .padding(top = 20.dp, start = 5.dp, end = 5.dp)
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .shadow(10.dp, RoundedCornerShape(30.dp), true, Color.Black, Color.Cyan)
                    .clip(
                        RoundedCornerShape(30.dp)
                    ),contentScale = ContentScale.FillBounds)
            Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "",modifier = Modifier
                .size(70.dp)
                .align(Alignment.TopEnd)
                .padding(top = 30.dp, end = 20.dp),tint = Color.LightGray
            )

            Image(painter = painterResource(id = R.drawable.h5), contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                    .align(Alignment.TopEnd),alignment = Alignment.TopEnd,contentScale = ContentScale.Fit)




        }
    }
}
@Composable
fun RateingBar(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
        Text(text = "Nike Air Max 97",fontSize = 20.sp,color = Color.White, fontWeight = FontWeight.ExtraBold)
        Row{
            for (i in 1..5){
                Icon(imageVector = Icons.Default.Star, contentDescription = null,tint = Color.Cyan.copy(0.5f))
            }}
    }


}
@Composable
fun ItemColor(){
    Column(Modifier.fillMaxWidth(),verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.Start) {
        Text(text = "COLORS",fontSize = 20.sp,color = Color.LightGray, fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Serif)
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Box(modifier = Modifier
                .size(40.dp)
                .background(Color.LightGray, CircleShape))
            Box(modifier = Modifier
                .size(40.dp)
                .background(Color.Blue, CircleShape))
            Box(modifier = Modifier
                .size(40.dp)
                .background(Color.Green, CircleShape))
            Box(modifier = Modifier
                .size(40.dp)
                .background(Color.Red, CircleShape))
            Box(modifier = Modifier
                .size(40.dp)
                .background(Color.Yellow, CircleShape))


        }
    }
}
@Composable
fun Action(){
    Row(modifier = Modifier
        .fillMaxSize()
        , horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
        Button(onClick = { /*TODO*/ },modifier = Modifier
            .height(60.dp)
            .weight(1f)
            .padding(end = 10.dp),shape =
        RoundedCornerShape(10.dp),colors = ButtonDefaults.buttonColors(containerColor = Color.White)) {
            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null,tint = Color.DarkGray)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "ADD TO CART",color = Color.DarkGray)

        }
        Button(onClick = { /*TODO*/ },modifier = Modifier
            .height(60.dp)
            .weight(1f),shape = RoundedCornerShape(10.dp)
        ) {
            Image(painter = painterResource(id = RM.pay), contentDescription = null,modifier = Modifier.size(50.dp))
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "BUY NOW",color = Color.White)
        }
    }
}