package com.example.sharedelement.shose_ui

import com.example.sharedelement.R


import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainContent(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier,
    showDetails: (cart: Cart, state: Boolean) -> Unit,
    list: List<Cart>
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(list) { _, item ->
            CartItem(
                cart = item,
                modifier = modifier,
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope,
                onClick = showDetails
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CartItem(
    cart: Cart,
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onClick: (cart: Cart, state: Boolean) -> Unit,
) {
    Card(
        modifier = Modifier
            .clickable { onClick(cart, true) }
            .fillMaxWidth()
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(20.dp))
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White // Use a light background for better contrast
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF6DD5FA), Color(0xFF2193B0)) // Gradient background
                    )
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            with(sharedTransitionScope) {
                Image(
                    painter = painterResource(id = cart.image),
                    contentDescription = null,
                    modifier = modifier
                        .sharedElement(
                            state = rememberSharedContentState(key = "image${cart.id}"),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                        .size(100.dp)
                        .clip(CircleShape)
                        .shadow(elevation = 4.dp, shape = CircleShape), // Add shadow to the image
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Text(
                        text = cart.title,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White // Use white text for better contrast
                        ),
                        modifier = Modifier
                            .sharedElement(
                                state = rememberSharedContentState(key = "text${cart.id}"),
                                animatedVisibilityScope = animatedVisibilityScope
                            ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = cart.body,
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.White.copy(alpha = 0.8f) // Slightly transparent text
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .sharedElement(
                                state = rememberSharedContentState(key = "body${cart.id}"),
                                animatedVisibilityScope = animatedVisibilityScope
                            ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

data class Cart(
    val id: Int,
    val image: Int,
    val title: String,
    val body: String
)

val carts = listOf(
    Cart(1, R.drawable.h1, "Hello Mostafa", "This is a sample description for the item."),
    Cart(2, R.drawable.h2, "Hello Mostafa", "This is a sample description for the item."),
    Cart(6, R.drawable.h3, "Hello Mostafa", "This is a sample description for the item."),
    Cart(3, R.drawable.h4, "Hello Mostafa", "This is a sample description for the item."),
    Cart(4, R.drawable.h5, "Hello Mostafa", "This is a sample description for the item."),
    Cart(5, R.drawable.h6, "Hello Mostafa", "This is a sample description for the item.")
)