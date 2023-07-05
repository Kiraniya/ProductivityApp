package com.example.productivity

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Timer(viewModel: TimerViewModel){
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("Pomodoro Timer", color = Color(0xFF703900))
        }, colors = TopAppBarDefaults.largeTopAppBarColors
            (MaterialTheme.colorScheme.primary))
    }) {

        Column(modifier=Modifier.fillMaxSize(), horizontalAlignment
        = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
            Box(modifier = Modifier
                .height(250.dp)
                .width(250.dp),
                contentAlignment = Alignment.Center){
                val progress = 1 - (viewModel.timeleft.value.toFloat() / viewModel.initialTotalTimeInMillis.toFloat())
                RoundedCornerIndicatorBg(
                    progress = progress ,
                    modifier =
                Modifier
                    .fillMaxSize()
                    .scale(scaleX = -1f, scaleY = 1f),
                    strokeWidth = 15.dp)
                Text(text = viewModel.timerText.value, style =
                MaterialTheme.typography
                    .displaySmall)
            }
            Spacer(modifier =Modifier.height(32.dp))
            Row(horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically){
                IconButton(onClick = { if(viewModel.isPlaying.value)
                    viewModel.stopCountDownTimer() else viewModel.startCountDownTimer()},
                    modifier =
                    Modifier
                        .size(80.dp)) {
                    Icon(if(viewModel.isPlaying.value) Icons
                        .Default.Stop
                    else Icons.Default.PlayArrow,
                        contentDescription
                        ="" ,modifier=Modifier.fillMaxSize())
                }
                Spacer(modifier =Modifier.width(100.dp))
                IconButton(onClick = { viewModel.resetCountDownTimer()},
                    modifier =
                    Modifier
                        .size(60.dp)) {
                    Icon(painterResource(id =R.drawable.reset ),
                        contentDescription
                        ="" ,modifier=Modifier.fillMaxSize())
                }
            }
            if(viewModel.ismainFinish.value){
                AlertDialog(
                    onDismissRequest = { viewModel.ismainFinish
                        .value=false},
                    confirmButton = {
                        TextButton(onClick = {viewModel.ismainFinish
                            .value=false
                            if(viewModel.count.value<3){viewModel
                                .startBreakTimer()}
                            else viewModel.startlbreakTimer()
                        })
                        { Text(text = "OK") }
                    }, title = {
                        Text(text = "Time for Break")
                    },
                    text = {
                        Text(
                            "Go grab some coffee!!"
                        )
                    }
                )
            }
            if(viewModel.isshortBfinish.value){
                AlertDialog(
                    onDismissRequest = {viewModel.isshortBfinish.value=false},
                    confirmButton = {
                        TextButton(onClick = {viewModel.isshortBfinish.value=false
                            viewModel.startCountDownTimer()})
                        { Text(text = "OK") }
                    },title = {
                        Text(text = "Get back to work!!")
                    },
                    text = {
                        Text(
                            "Either you run the day or the day runs you!!"
                        )
                    }
                )
            }
            if(viewModel.islongBfinish.value){
                AlertDialog(
                    onDismissRequest = {viewModel.islongBfinish.value=false},
                    confirmButton = {
                        TextButton(onClick = {viewModel.islongBfinish.value=false
                            viewModel.resetCountDownTimer()})
                        { Text(text = "OK") }
                    },title = {
                        Text(text = "You completed a cycle!!")
                    },
                    text = {
                        Text(
                            "Keep pushing."
                        )
                    }
                )
            }

        }
    }

}
