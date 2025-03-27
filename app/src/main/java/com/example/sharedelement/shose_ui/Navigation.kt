package com.example.sharedelement.shose_ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.mycompose.ui.theme.presentation.CartBuy.ShoeCard
import com.example.sharedelement.ui.theme.SharedelementTheme


sealed class Screen {
    data object List : Screen()
    data object Details : Screen()
}
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedbyAnimation(){
    SharedelementTheme {
        var showDetails by remember { mutableStateOf<Screen>(Screen.List) }
        var car by remember { mutableStateOf(com.example.mycompose.ui.theme.presentation.CartBuy.carts[0]) }
        SharedTransitionLayout {
            AnimatedContent(targetState = showDetails, label = "AnimatedContent")
            /*transitionSpec = {
               if (targetState == Screen.List){
                   slideInHorizontally(animationSpec = spring(100f)){-it} + fadeIn() togetherWith  slideOutHorizontally(animationSpec = spring(100f)){it} + fadeOut()


               }
                    else{
                        slideInHorizontally(animationSpec = spring(100f)){-it} + fadeIn() togetherWith  slideOutHorizontally(animationSpec = spring(100f)) {it}+ fadeOut()
                      }



             })*/
            {

                when(it) {
                    Screen.List ->{
                      //  Box(modifier = Modifier.fillMaxSize().background(Color.White))
                        com.example.mycompose.ui.theme.presentation.CartBuy.MainContent(sharedTransitionScope = this@SharedTransitionLayout,
                            animatedVisibilityScope = this@AnimatedContent, modifier = Modifier,
                            showDetails = {cart,state->
                                car =cart
                                if (state) showDetails = Screen.Details
                            }, list = com.example.mycompose.ui.theme.presentation.CartBuy.carts
                        )}
                    Screen.Details->{
                        Box(modifier = Modifier.fillMaxSize().background(Color.White))
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
}





