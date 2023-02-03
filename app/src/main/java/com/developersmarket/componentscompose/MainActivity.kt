package com.developersmarket.componentscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.developersmarket.componentscompose.ui.theme.ComponentsComposeTheme
import com.developersmarket.componentscompose.util.User
import com.developersmarket.componentscompose.util.dummyData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComponentsComposeTheme {
                Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                ) {

                    Recyclerview(users = dummyData())
                }
            }
        }
    }

    @Composable fun EachRow(user: User) {
        Card(
                modifier = Modifier.padding(
                        horizontal = 8.dp,
                        vertical = 8.dp
                ).fillMaxWidth(),
                shape = RoundedCornerShape(CornerSize(10.dp)),
                elevation = 2.dp
        ) {
            Row(modifier = Modifier.padding(5.dp)) {
                Image(
                        painter = painterResource(id = R.drawable.dummy_girl),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(124.dp)
                            .align(Alignment.CenterVertically)
                            .clip(RoundedCornerShape(10.dp))
                )
                
               Text(text = user.description, modifier = Modifier.padding(8.dp))
            }
        }
    }

    @Composable fun Recyclerview(users:List<User>) {
        LazyColumn {
            items(users){user->
                EachRow(user=user)
            }
        }
    }
}

