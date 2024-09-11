package com.example.to_do_list

import android.app.Dialog
import android.app.LocaleConfig
import android.app.TimePickerDialog
import android.icu.text.RelativeDateTimeFormatter.RelativeDateTimeUnit
import android.text.format.DateUtils
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun To_Do_List() {
    var taskList = remember {
        mutableListOf("")
    }

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

            Column(modifier = Modifier
                .fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment =  Alignment.CenterHorizontally
                ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height((LocalConfiguration.current.screenHeightDp - 180).dp)
                    , verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                    Task_card()
                    Task_card()
                    Task_card()
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.AddCircle, contentDescription = null, modifier = Modifier.size(60.dp), tint = Color.White)
                }
            }
        }

    }
    Add_task()


}
@Composable
fun Task_card() {
    var buttonPressed by remember {mutableStateOf(false)}
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .clip(
            RoundedCornerShape(20.dp)
        )
        .background(Color.White)

        .border(2.dp, Color.Black, shape = RoundedCornerShape(20.dp))){

        Row(modifier = Modifier
            .fillMaxSize()
            , verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { buttonPressed = !buttonPressed }) {
                Icon(Icons.Filled.Done, contentDescription= null, modifier = Modifier.padding(horizontal = 10.dp), tint = if(buttonPressed) Color.Green else Color.Black)
            }

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

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Add_task() {
    var task by remember{mutableStateOf("")}
    var date by remember{ mutableStateOf("") }
    var time = remember{mutableStateOf("")}
    val datePicker = rememberDatePickerState()
    val timePicker = rememberTimePickerState()
    var dateWindow by remember {
        mutableStateOf(false)
    }
    var timeWindow by remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

        Box(modifier = Modifier
            .width((LocalConfiguration.current.screenWidthDp - 50).dp)
            .height((LocalConfiguration.current.screenHeightDp - 400).dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(10.dp))){


            Column(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 50.dp), verticalArrangement =Arrangement.spacedBy(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                TextField(modifier = Modifier
                    .fillMaxWidth()
                    .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(10.dp)),placeholder = { Text(text = "Task", fontWeight = FontWeight.Bold)},value = task, onValueChange = { newTask -> task = newTask},
                    colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent, unfocusedTextColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent, focusedContainerColor = Color.White))
                TextButton(onClick = { dateWindow = true }, modifier = Modifier
                    .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .height(55.dp)) {
                    Text(text = date.ifEmpty { "Select Date" }, fontWeight = FontWeight.Bold, color = Color.DarkGray, fontSize = 15.sp, modifier = Modifier.fillMaxWidth())
                }
                if(dateWindow){
                    DatePickerDialog(onDismissRequest = {  }, dismissButton = { TextButton(onClick = { dateWindow = false }) {
                        Text(text = "Close")
                    }
                                                                             },confirmButton = { TextButton(onClick = { date = datePicker.selectedDateMillis.toString()
                    dateWindow = false}) {
                        Text(text = "Select")
                    } }) {
                        DatePicker(state = datePicker)
                    }
                }
                Dialog
                
            }

        }
    }
}