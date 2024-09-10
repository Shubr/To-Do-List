package com.example.to_do_list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun To_Do_List() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(
            Brush.linearGradient(
                listOf(
                    Color(0xFFFFFFFF),
                    Color(0xFFFFB297),
                    Color(0xFFFF3956)
                )
            )
        )){
        Column(modifier = Modifier.padding(horizontal = 10.dp)) {
            Text(text = "To-Do", fontSize = 40.sp, fontWeight = FontWeight.Bold,modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp), textAlign = TextAlign.Center)

            Task_card()
        }

    }

}
@Composable
fun Task_card() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .background(Color.Transparent)
        .clip(
            RoundedCornerShape(20.dp)
        )
        .border(2.dp, Color.Black, shape = RoundedCornerShape(20.dp))){

        Row(modifier = Modifier
            .fillMaxSize()
            , verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Filled.Done, contentDescription= null, modifier = Modifier.padding(horizontal = 10.dp))
            VerticalDivider(thickness = (1.5).dp, color = Color.Black)
            Column(modifier = Modifier.fillMaxHeight()) {
                Text(text = "Monday 20 2:30", fontSize = 20.sp,modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp), textAlign = TextAlign.Center)
                Text(text = "Clean up the house", fontWeight = FontWeight.Bold, fontSize = 25.sp, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp), textAlign = TextAlign.Center)
            }

        }
    }

}