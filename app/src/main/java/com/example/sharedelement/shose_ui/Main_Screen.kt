package com.example.sharedelement.shose_ui


import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.sharp.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharedelement.R
import com.example.sharedelement.shose_ui.cons.body
import com.example.sharedelement.shose_ui.cons.title


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ListScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemClick: (Int,Int ,String,String) -> Unit
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(list1) { index, resId ->
            ShoesItem(index, onItemClick, resId, animatedVisibilityScope)
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.ShoesItem(
    index: Int,
    onItemClick: (Int,Int, String, String) -> Unit,
    product: Shoe,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(product.id, product.image, product.text, product.body) }
    ) {
        Image(
            painter = painterResource(id = product.image),
            contentDescription = null,
            modifier = Modifier
                .weight(0.5f)
                .sharedElement(
                    state = rememberSharedContentState(key = "image/${product.id}"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = { _, _ ->
                        tween(durationMillis = 1000)
                    }
                )
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {

            Text(
                text = product.text,
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = "text/${product.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 1000)
                        }
                    ), maxLines = 1,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                color = Color.Black,
                softWrap = true,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis

            )
            Text(
                text = product.body,
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = "body/${product.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 1000)
                        }
                    ), maxLines = 2
            )


        }
    }
}

/*@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListSaggerad(){
    ListScr ()
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListScr(

) {
    val draggableState= rememberDraggableState {

    }

    val drawables = list1

    LazyColumn(
        modifier = Modifier.fillMaxSize()
        , contentPadding = PaddingValues(10.dp)
    ,){
        items(drawables.size, key = {it}){item->
           Column(modifier = Modifier
               .background(Color.LightGray)
               .animateItemPlacement(tween(1000))) {
                Image(
                    painter = painterResource(id = item),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.clip(shape = RoundedCornerShape(10))
                )





            }
        }
    }
}*/
class Shoe(val id :Int,val image:Int,val text:String,val body:String){}
val list1 = listOf(Shoe(1,RM.h5,title,body),Shoe(2,RM.h6,title,body),
    Shoe(3,RM.h1,title,body),
    Shoe(4,RM.h2,title,body),
    Shoe(5,RM.h4,title,body),
    Shoe(6,RM.h6,title,body),
    Shoe(7,RM.h1,title,body),
    Shoe(8,RM.h3,title,body),

)



object cons {
    val title = "Item  ANMAD"
    val body = "Item this Shose very good ANMAD \n" +
            " it is very good bodyShoes are more than just a fashion statement;" +
            " they are a necessity. But with so many options out there, " +
            "it can be overwhelming to choose the right pair. That's where we come " +
            "in! \"Shoe Zero\" is your go-to place for not just buying shoes, but creating them." +
            " Yes, you heard it right! We offer a \n" +
            "wide range of customizable shoes to cater to your unique style and needs"
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListScreenPreview() {
    var seach  by remember { mutableStateOf("") }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .background(Color.Black)) {
            Toolbar()
            Spacer(modifier = Modifier.height(20.dp))

            Search(seach)
            Spacer(modifier = Modifier.height(10.dp))

            TrandItem()
            Spacer(modifier = Modifier.height(40.dp))

            DividerTrand()

            ProductItem()
        }

    }
}

@Composable
private fun Search(seach: String) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        var seach1 = seach
        OutlinedTextField(
            value = seach1,
            onValueChange = { seach1 = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Search", color = Color.Green.copy(alpha = 0.5f),
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
            },
            trailingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription ="Search",modifier = Modifier.size(40.dp) )
            },)
        /*    colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Blue.copy(alpha = 0.6f),
            ))*/


    }}

@Composable
private fun Toolbar() {
    Row(
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.list),
            contentDescription = "list_icon", modifier = Modifier.size(40.dp)
        )
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
//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TrandItem(){
    val  scaleFloat = rememberInfiniteTransition()
    var scale =scaleFloat.animateFloat(
        initialValue = 1.2f,
        targetValue = .8f,
        animationSpec = infiniteRepeatable(animation = tween(10000), repeatMode = RepeatMode.Reverse)
    )

    LazyRow {
        items(10){
            Column(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxHeight(.54f)
                    .fillParentMaxWidth(0.8f)
                    .background(Color.Black)){
                    Image(painter = painterResource(id = R.drawable.bac), contentDescription = null,
                        modifier = Modifier
                            .padding(top = 20.dp, start = 5.dp, end = 15.dp)
                            .size(300.dp)
                            .align(Alignment.Center)
                            .clip(
                                RoundedCornerShape(30.dp)
                            ),contentScale = ContentScale.FillBounds)
                    Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "",modifier = Modifier
                        .size(70.dp)
                        .align(Alignment.TopEnd)
                        .padding(top = 30.dp, end = 20.dp),tint = Color.LightGray
                    )

                    Image(painter = painterResource(id = R.drawable.h1), contentDescription = null,
                        modifier = Modifier
                            .size(250.dp)
                            .scale(scale.value)
                            .align(Alignment.TopEnd),contentScale = ContentScale.Fit)
                    Column(modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(20.dp)) {
                        Text(text = "Nike Air Max 97",fontSize = 20.sp,color = Color.White, fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(bottom = 10.dp),
                            fontFamily = FontFamily.SansSerif)
                        Text(text = "MAX 260",fontSize = 18.sp,color = Color.White, fontWeight = FontWeight.Bold)
                        Text(text = "$ 150",fontSize = 18.sp,color = Color.White, fontWeight = FontWeight.Black)



                    }

                }}
        }
    }

}
@Composable
fun DividerTrand(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)){
        //HorizontalDivide(color = Color.Gray, thickness = 2.dp)

    }
}
//@Preview(showBackground = true, showSystemUi = true)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Greeting(name: String) {
    val state = rememberPagerState(pageCount = {10})

    HorizontalPager(state =state ) {page->
        when(page){
            1->{}
            2->{}
            3->{}
            4->{}
            5->{}
            6->{}
            7->{}
            8->{}
            9->{}
            10->{}

        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductItem(){
    LazyRow {
        items(10){
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                Box(modifier = Modifier
                    .align(Alignment.CenterHorizontally)

                    .fillParentMaxWidth(0.45f)
                    .background(Color.Black)){
                    Box(modifier = Modifier
                        .padding(top = 40.dp, start = 1.dp, end = 30.dp, bottom = 40.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color.White,
                                    Color.LightGray, Color.Cyan.copy(alpha = 0.2f)
                                )
                            ), shape = RoundedCornerShape(20.dp)
                        )
                        .size(200.dp)) {
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
                                .padding(10.dp)
                            ,tint = Color.White
                        )
                    }
                    Image(painter = painterResource(id = R.drawable.h6), contentDescription = null,
                        modifier = Modifier
                            .width(200.dp)
                            .height(200.dp)
                            .rotate(-30f)
                            .padding(start = 10.dp)
                            .align(Alignment.TopEnd),contentScale = ContentScale.Fit, alignment = Alignment.TopEnd
                    )
                    Text(text = "Nike Air Max 97",fontSize = 20.sp,color = Color.LightGray, fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(end = 10.dp),
                        textAlign = TextAlign.Start)

                }}
        }
    }

}
