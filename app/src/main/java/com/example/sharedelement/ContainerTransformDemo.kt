@file:OptIn(
    ExperimentalSharedTransitionApi::class, ExperimentalSharedTransitionApi::class,
    ExperimentalSharedTransitionApi::class
)

package com.example.mycompose.ui.theme.presentation.CartBuy

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sharedelement.R


@Composable
fun GreetingPreview(Modifier: Modifier) {
    SharedbyAnimation()
}


sealed class Screen {
    data object List : Screen()
    data object Details : Screen()
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedbyAnimation() {

    var showDetails by remember { mutableStateOf<Screen>(Screen.Details) }
    var car by remember { mutableStateOf(carts[0]) }
    SharedTransitionLayout {
        AnimatedContent(targetState = showDetails, label = "AnimatedContent",
            transitionSpec = {
                if (targetState == Screen.List) {
                    slideInHorizontally(animationSpec = spring(100f)) { -it } + fadeIn() togetherWith slideOutHorizontally(
                        animationSpec = spring(100f)
                    ) { it } + fadeOut()


                } else {
                    slideInHorizontally(animationSpec = spring(100f)) { -it } + fadeIn() togetherWith slideOutHorizontally(
                        animationSpec = spring(100f)
                    ) { it } + fadeOut()
                }


            })
        {

            when (it) {
                Screen.List -> {
                    /*  MainContent(sharedTransitionScope = this@SharedTransitionLayout,
                          animatedVisibilityScope = this@AnimatedContent, modifier = Modifier,
                          showDetails = {cart,state->
                              car =cart
                              if (state) showDetails = Screen.Details
                          }, list = carts)*/
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red))
                }

                Screen.Details -> {
                    ShoeCard(
                        onClick = { showDetails = Screen.List },
                        state = true,
                        cart = car,
                        animatedContentScope = this@AnimatedContent
                    )
                }

            }


        }
    }
}


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


@Composable
fun SharedTransitionScope.ShoeCard(
    onClick: () -> Unit,
    state: Boolean,
    animatedContentScope: AnimatedVisibilityScope,
    cart: Cart
) {
    var selectedColor by remember { mutableStateOf(Color.Red) }
    val containerColor by animateColorAsState(
        targetValue = selectedColor,
        animationSpec = tween(durationMillis = 1000, delayMillis = 100),
        label = "color"
    )

    ShoeCardContent(
        state = state,
        onClick = onClick,
        containerColor = containerColor,
        onColorSelected = { selectedColor = it },
        cart = cart,
        animatedContentScope = animatedContentScope,
        sharedTransitionScope = this
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun ShoeCardContent(
    state: Boolean,
    onClick: () -> Unit,
    containerColor: Color,
    onColorSelected: (Color) -> Unit,
    cart: Cart,
    animatedContentScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope
) {
    with(sharedTransitionScope) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .sharedElement(
                    state = rememberSharedContentState(key = "cart${cart.id}"),
                    animatedVisibilityScope = animatedContentScope
                )
                .background(containerColor.copy(alpha = 0.5f))
        ) {
            val (imageRef, cardRef, colorListRef) = createRefs()
            val topGuideline = createGuidelineFromTop(0.5f)



            Card(
                modifier = Modifier
                    .constrainAs(cardRef) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)),
                colors = CardDefaults.cardColors(containerColor),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Text(
                    text = cart.body,
                    modifier = Modifier
                        .padding(10.dp, 90.dp, 10.dp, 10.dp)
                        .sharedElement(
                            state = rememberSharedContentState(key = "body${cart.id}"),
                            animatedVisibilityScope = animatedContentScope
                        ),
                    color = Color.White,
                    style = TextStyle(fontSize = 18.sp)
                )
            }

            val animatedSize by animateSizeAsState(
                targetValue = if (state) Size(400f, 800f) else Size(100f, 100f),
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )

            Image(
                painter = painterResource(cart.image),
                contentDescription = null,
                modifier = Modifier
                    .clickable { onClick() }
                    .sharedElement(
                        state = rememberSharedContentState(key = "image${cart.id}"),
                        animatedVisibilityScope = animatedContentScope
                    )
                    .constrainAs(imageRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(cardRef.top)
                    }
                    .width(animatedSize.width.dp)
                    .height(animatedSize.height.dp)
                    .clip(if (state) RoundedCornerShape(20.dp) else CircleShape),
                contentScale = ContentScale.FillBounds
            )

        }
    }
}

