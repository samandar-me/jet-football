package com.sdk.footballclubs.ui.screen.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.sdk.footballclubs.data.Club
import com.sdk.footballclubs.ui.screen.club.ClubScreen
import com.skydoves.landscapist.glide.GlideImage
import org.koin.androidx.compose.getViewModel

object HomeScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: HomeViewModel = getViewModel()
        val clubList = viewModel.state.value.clubList
        val error = viewModel.state.value.error
        val isLoading = viewModel.state.value.isLoading
        val navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            if (isLoading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            clubList?.let {
                LazyColumn {
                    items(it) { club ->
                        ClubItem(club) {
                            navigator push ClubScreen(club.id, navigator)
                        }
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun ClubItem(club: Club, onItemClick: () -> Unit) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            shape = RoundedCornerShape(12.dp),
            onClick = onItemClick,
            elevation = 5.dp
        ) {
            LaunchedEffect(key1 = Unit) {
                Log.d("@@@", club.imageUrl)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                GlideImage(
                    imageModel = club.imageUrl,
                    modifier = Modifier.size(100.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = club.name,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}