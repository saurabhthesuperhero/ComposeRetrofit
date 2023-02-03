package com.developersmarket.componentscompose.retrofit.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.developersmarket.componentscompose.retrofit.Post
import com.developersmarket.componentscompose.ui.theme.ComponentsComposeTheme
import com.developersmarket.componentscompose.util.ApiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RetrofitActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComponentsComposeTheme() {
                Surface(color = MaterialTheme.colors.background) {
                    GETData(mainViewModel)
                }
            }
        }
    }

    @Composable fun EachRow(post: Post) {
        Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                            horizontal = 8.dp,
                            vertical = 8.dp
                    ),
                elevation = 2.dp,
                shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                    text = post.body,
                    modifier = Modifier.padding(10.dp)
            )
        }

    }

    @Composable fun GETData(mainViewModel: MainViewModel) {
        when (val result = mainViewModel.response.value) {
            is ApiState.Success -> {
                LazyColumn {
                    items(result.data) { response ->
                        EachRow(post = response)
                    }
                }
            }

            is ApiState.Failure -> {
                Text(text = "${result.msg}")
            }

            ApiState.Loading -> {
                CircularProgressIndicator()
            }

            ApiState.Empty -> {

            }
        }
    }
}