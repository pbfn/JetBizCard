package com.pedro_bruno.jetbizcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.pedro_bruno.jetbizcard.ui.theme.JetBizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetBizCardTheme(
                darkTheme = false
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard() {
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(12.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(
                corner = CornerSize(15.dp),
            ),
            backgroundColor = Color.White
        ) {
            Column(
                modifier = Modifier
                    .height(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateImageProfile(R.drawable.profile_image)
                Divider()
                CreateInfo(
                    name = "Pedro Bruno",
                    profession = "Android Compose Programmer",
                    twitter = "@pedrobrunof"
                )
                Button(
                    onClick = {
                        buttonClickedState.value = !buttonClickedState.value
                    },
                ) {
                    Text(
                        text = "Portifólio",
                        style = MaterialTheme.typography.button
                    )
                }

                AnimatedVisibility(visible = buttonClickedState.value) {
                    Content()
                }
            }

        }
    }
}

@Composable
private fun CreateInfo(name: String, profession: String, twitter: String) {
    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            text = name,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant
        )
        Text(
            text = profession,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = twitter,
            modifier = Modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.subtitle1
        )
    }
}


@Composable
fun CreateImageProfile(@DrawableRes imageId: Int, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(width = 0.5.dp, Color.LightGray),
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = "Image Profile",
            modifier = modifier
                .size(135.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun Content() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Surface(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, color = Color.LightGray)
        ) {
            Portfolio(
                data = listOf(
                    "Project 1",
                    "Project 2",
                    "Project 3",
                    "Project 4",
                    "Project 5",
                    "Project 6",
                    "Project 7",
                    "Project 8",
                    "Project 9",
                    "Project 10",
                )
            )
        }
    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn {
        items(data) { item ->

            ItemPortfolio(nameProject = item, imageProfile = R.drawable.profile_image)
        }
    }
}


@Composable
fun ItemPortfolio(nameProject: String, @DrawableRes imageProfile: Int) {
    Card(
        modifier = Modifier
            .padding(13.dp)
            .fillMaxWidth(),
        shape = RectangleShape,
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .background(MaterialTheme.colors.surface)
                .padding(7.dp)
        ) {
            CreateImageProfile(
                imageId = imageProfile,
                modifier = Modifier.size(100.dp)
            )

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .align(alignment = Alignment.CenterVertically)
            ) {
                Text(
                    text = nameProject,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "A great Project",
                    style = MaterialTheme.typography.body2
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetBizCardTheme {
        CreateBizCard()
    }
}