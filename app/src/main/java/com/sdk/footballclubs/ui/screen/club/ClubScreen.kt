package com.sdk.footballclubs.ui.screen.club

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.skydoves.landscapist.glide.GlideImage
import org.koin.androidx.compose.getViewModel

data class ClubScreen(
    val id: Int,
    val navigator: Navigator
) : Screen {
    @Composable
    override fun Content() {
        val viewModel: ClubViewModel = getViewModel()
        LaunchedEffect(key1 = Unit) {
            viewModel.getClubById(id)
        }
        val club = viewModel.state.value.club
        Scaffold(
            topBar = {
                IconButton(onClick = { navigator.pop() }, modifier = Modifier.padding(7.dp)) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
            }
        ) {
            club?.let {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp)
                ) {
                    Column {
                        GlideImage(
                            imageModel = club.imageUrl,
                            modifier = Modifier
                                .size(150.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = club.name,
                            fontSize = 22.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = club.leagueName,
                                fontSize = 20.sp,
                                color = Color.Green,
                                fontStyle = FontStyle.Italic
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = "",
                                tint = Color.Black
                            )
                            Text(
                                text = club.rating.toString(),
                                fontSize = 15.sp,
                                color = Color.Gray,
                                textAlign = TextAlign.Center
                            )
                        }
                        Text(
                            text = club.desc,
                            fontSize = 14.sp,
                            color = Color.Gray,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }
            }
        }
    }
}
