package com.example.to_do_list

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun To_Do_List() {
    var taskList by remember {
        mutableStateOf(listOf<Triple<String,String,String>>())
    }

    var addTaskWindow by remember {
        mutableStateOf(false)
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
                    LazyColumn {
                        items(taskList){task->
                            Task_card(task = task.first, date = task.second, time = task.third)
                        }
                    }
                }
                IconButton(onClick = { addTaskWindow = !addTaskWindow }) {
                    Icon(Icons.Filled.AddCircle, contentDescription = null, modifier = Modifier.size(60.dp), tint = Color.White)
                }
            }
        }

    }
    if(addTaskWindow){
        Add_task {
            task -> taskList = taskList + task
        }
    }


}
@Composable
fun Task_card(task:String, date:String, time:String) {
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
                Text(text = "$date $time", fontSize = 20.sp,modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp), textAlign = TextAlign.Center)
                Text(text =  task, fontWeight = FontWeight.Bold, fontSize = 25.sp, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp), textAlign = TextAlign.Center)
            }

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Add_task(onTaskAdd:(Triple<String, String, String>)->Unit) {
    var task by remember{mutableStateOf("")}
    var date by remember{ mutableStateOf("") }
    var time by remember{mutableStateOf("")}
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
            .height((LocalConfiguration.current.screenHeightDp - 500).dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(10.dp))){


            Column(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 50.dp), verticalArrangement =Arrangement.spacedBy(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Add-Task", fontSize = 30.sp, fontWeight = FontWeight.Bold)
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
                TextButton(onClick = { timeWindow = true }, modifier = Modifier
                    .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .height(55.dp)) {
                    Text(text = time.ifEmpty { "Select Time" }, fontWeight = FontWeight.Bold, color = Color.DarkGray, fontSize = 15.sp, modifier = Modifier.fillMaxWidth())
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    TextButton(onClick = {
                        if(task.isNotEmpty() || date.isNotEmpty() || time.isNotEmpty()){
                            onTaskAdd(Triple(task,date,time))
                        }
                    }) {
                        Text(text = "Add", fontWeight = FontWeight.Bold, color = Color.Black)
                    }
                }
                if(dateWindow){
                    DatePickerDialog(onDismissRequest = {  }, dismissButton = { TextButton(onClick = { dateWindow = false }) {
                        Text(text = "Close")
                    } },confirmButton = { TextButton(onClick = { val formatted = datePicker.selectedDateMillis?.let {
                        SimpleDateFormat("dd/MM", Locale.getDefault()).format(Date(it))
                    } ?: "No date Selected"
                        date = formatted
                    dateWindow = false}) {
                        Text(text = "Select")
                    } }) {
                        DatePicker(state = datePicker)
                    }
                }
                if(timeWindow){
                    AlertDialog(
                        onDismissRequest = {  },
                        dismissButton = {
                            TextButton(onClick = { timeWindow = false}) {
                                Text("Dismiss")
                            }
                        },
                        confirmButton = {
                            TextButton(onClick = {
                                val calander = java.util.Calendar.getInstance()
                                calander.set(java.util.Calendar.HOUR_OF_DAY, timePicker.hour)
                                calander.set(java.util.Calendar.MINUTE, timePicker.minute)

                                val formatted = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(calander.time)
                                time = formatted
                                timeWindow = false
                            }) {
                                Text("OK")
                            }
                        },
                        text = { TimePicker(state =timePicker) }
                    )
                }
            }

        }
    }
}
