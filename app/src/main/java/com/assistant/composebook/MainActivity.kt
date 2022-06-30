package com.assistant.composebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.assistant.composebook.ui.theme.MsgData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Conversation(messages = MsgData.messages)
        }
    }

    @Composable
    fun Conversation(messages: List<Message>) {
        LazyColumn {
            items(
                key = { messages[it].body },
                count = messages.size,
                    itemContent = { index ->
                        val cartItemData = messages[index]
                        MessageCard( cartItemData)
                })

        }
    }



    @Composable
    fun MessageCard(msg: Message) {
        Surface(
            modifier = Modifier.padding(all = 8.dp),
            tonalElevation = 8.dp
        ) {

            Row(modifier = Modifier.padding(all = 8.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.ivatart),
                    contentDescription = "Contact profile picture",
                    modifier = Modifier
                        // Set image size to 40 dp
                        .size(30.dp)
                        // Clip image to be shaped as a circle
                        .clip(CircleShape)
                        .border(1.5.dp, MaterialTheme.colorScheme.secondary, shape = CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = msg.author,
                        color = MaterialTheme.colorScheme.secondary // 添加颜色
                    )
                    Text(text = msg.body)
                }
            }
        }
    }

    @Preview
    @Composable
    fun PreviewMessageCard() {
      Conversation(messages = MsgData.messages)
    }
}

data class Message(val author: String, val body: String)