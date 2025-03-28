@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.sharedelement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharedelement.shose_ui.SharedbyAnimation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


                   SharedbyAnimation()


        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
/*
@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalAnimationApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SharedTransitionScope.SharedBoundsExample() {
    var isExpanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AnimatedContent(
            label = "bounds",
            targetState = isExpanded,
            transitionSpec = { fadeIn() togetherWith  fadeOut() }
        ) { expanded ->
            Box(
                modifier = Modifier.sharedBounds(
                    animatedVisibilityScope = this@AnimatedContent,
                    sharedContentState = rememberSharedContentState(key = expanded.toString())
                )
            ) {
                Box(
                    modifier = Modifier
                        .size(if (expanded) 200.dp else 100.dp)
                        .background(if (expanded) Color.Blue else Color.Red),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (expanded) "Expanded" else "Collapsed",
                        color = Color.White
                    )
                }
            }
        }
    }

}*/

@OptIn(ExperimentalSharedTransitionApi::class)
private class SharedElementBasicUsage2 {

    @Composable
    fun SharedElementApp() {
        // [START android_compose_animations_shared_element_step1]
        var showDetails by remember {
            mutableStateOf(false)
        }
        SharedTransitionLayout {
            AnimatedContent(
                transitionSpec=  {
                    (fadeIn(animationSpec = tween(1020, delayMillis = 90)) +
                scaleIn(initialScale = 0.92f, animationSpec = tween(1020, delayMillis = 90)))
                        .togetherWith(fadeOut(animationSpec = tween(90)))},
                targetState = showDetails,
                label = "basic_transition",

            ) { targetState ->
                if (!targetState) {
                    MainContent(
                        onShowDetails = {
                            showDetails = true
                        },
                        animatedVisibilityScope = this@AnimatedContent,
                        sharedTransitionScope = this@SharedTransitionLayout
                    )
                } else {
                    DetailsContent(
                        onBack = {
                            showDetails = false
                        },
                        animatedVisibilityScope = this@AnimatedContent,
                        sharedTransitionScope = this@SharedTransitionLayout
                    )
                }
            }
        }
        // [END android_compose_animations_shared_element_step1]
    }

    @OptIn(ExperimentalSharedTransitionApi::class)
    @Composable
    private fun MainContent(
        onShowDetails: () -> Unit,
        sharedTransitionScope: SharedTransitionScope,
        animatedVisibilityScope: AnimatedVisibilityScope
    ) {
        Row(
            // [START_EXCLUDE]
            modifier = Modifier
                .padding(8.dp)
                .border(1.dp, Color.Gray.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                .background(Color.Blue, RoundedCornerShape(8.dp))
                .clickable {
                    onShowDetails()
                }
                .padding(8.dp)
            // [END_EXCLUDE]
        ) {
            Image(
                painter = painterResource(id = R.drawable.h3),
                contentDescription = "Cupcake",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            // [START_EXCLUDE]
            Text("Cupcake", fontSize = 21.sp)
            // [END_EXCLUDE]
        }
    }

    @Composable
    private fun DetailsContent(
        modifier: Modifier = Modifier,
        onBack: () -> Unit,
        sharedTransitionScope: SharedTransitionScope,
        animatedVisibilityScope: AnimatedVisibilityScope
    ) {
        Column(
            // [START_EXCLUDE]
            modifier = Modifier
                .padding(top = 200.dp, start = 16.dp, end = 16.dp)
                .border(1.dp, Color.Gray.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                .background(Color.Blue, RoundedCornerShape(8.dp))
                .clickable {
                    onBack()
                }
                .padding(8.dp)
            // [END_EXCLUDE]
        ) {
            Image(
                painter = painterResource(id = R.drawable.h1),
                contentDescription = "Cupcake",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            // [START_EXCLUDE]
            Text("Cupcake", fontSize = 28.sp)
            Text(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sit amet lobortis velit. " +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                        " Curabitur sagittis, lectus posuere imperdiet facilisis, nibh massa " +
                        "molestie est, quis dapibus orci ligula non magna. Pellentesque rhoncus " +
                        "hendrerit massa quis ultricies. Curabitur congue ullamcorper leo, at maximus"
            )
            // [END_EXCLUDE]
        }
    }}


/*
 * Copyright 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


@Preview
@Composable
fun SharedBoundsDemo() {
    var showDetails by remember {
        mutableStateOf(false)
    }
    SharedTransitionLayout {
        AnimatedContent(
            showDetails,
            label = "basic_transition"
        ) { targetState ->

            if (!targetState) {
                MainContent(
                    onShowDetails = {
                        showDetails = true
                    },
                    animatedVisibilityScope = this@AnimatedContent,
                    sharedTransitionScope = this@SharedTransitionLayout
                )
            } else {
                DetailsContent(
                    onBack = {
                        showDetails = false
                    },
                    animatedVisibilityScope = this@AnimatedContent,
                    sharedTransitionScope = this@SharedTransitionLayout
                )
            }
        }
    }
}

// [START android_compose_animations_shared_element_shared_bounds]
@Composable
private fun MainContent(
    onShowDetails: () -> Unit,
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    with(sharedTransitionScope) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .sharedBounds(
                    rememberSharedContentState(key = "bounds"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    enter = fadeIn(),
                    exit = fadeOut(),
                    resizeMode = SharedTransitionScope.ResizeMode.ScaleToBounds()
                )
                // [START_EXCLUDE]
                .border(1.dp, Color.Gray.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                .background(Color.Yellow, RoundedCornerShape(8.dp))
                .clickable {
                    onShowDetails()
                }
                .padding(8.dp)
            // [END_EXCLUDE]
        ) {
            // [START_EXCLUDE]
            Image(
                painter = painterResource(id = R.drawable.h2),
                contentDescription = "Cupcake",
                modifier = Modifier
                    .sharedElement(
                        rememberSharedContentState(key = "image"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Text(
                "Cupcake", fontSize = 21.sp,
                modifier = Modifier.sharedBounds(
                    rememberSharedContentState(key = "title"),
                    animatedVisibilityScope = animatedVisibilityScope
                )
            )
            // [END_EXCLUDE]
        }
    }
}

@Composable
private fun DetailsContent(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    with(sharedTransitionScope) {
        Column(
            modifier = Modifier
                .padding(top = 200.dp, start = 16.dp, end = 16.dp)
                .sharedBounds(
                    rememberSharedContentState(key = "bounds"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    enter = fadeIn(),
                    exit = fadeOut(),
                    resizeMode = SharedTransitionScope.ResizeMode.ScaleToBounds()
                )
                // [START_EXCLUDE]
                .border(1.dp, Color.Gray.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                .background(Color.Yellow, RoundedCornerShape(8.dp))
                .clickable {
                    onBack()
                }
                .padding(8.dp)
            // [END_EXCLUDE]

        ) {
            // [START_EXCLUDE]
            Image(
                painter = painterResource(id = R.drawable.h2),
                contentDescription = "Cupcake",
                modifier = Modifier
                    .sharedElement(
                        rememberSharedContentState(key = "image"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .size(200.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Text(
                "Cupcake", fontSize = 28.sp,
                modifier = Modifier.sharedBounds(
                    rememberSharedContentState(key = "title"),
                    animatedVisibilityScope = animatedVisibilityScope
                )
            )
            Text(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sit amet lobortis velit. " +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                        " Curabitur sagittis, lectus posuere imperdiet facilisis, nibh massa " +
                        "molestie est, quis dapibus orci ligula non magna. Pellentesque rhoncus " +
                        "hendrerit massa quis ultricies. Curabitur congue ullamcorper leo, at maximus"
            )
            // [END_EXCLUDE]
        }
    }
}
// [END android_compose_animations_shared_element_shared_bounds]