package com.example.productivity

import android.os.CountDownTimer
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productivity.TimeForm.timeFormat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class TimerViewModel:ViewModel() {
    private var countDownTimer: CountDownTimer?=null
    private val userInputMinutes= TimeUnit.MINUTES.toMillis(1)
    private val shortBreak=TimeUnit.MINUTES.toMillis(2)
    private val longBreak=TimeUnit.MINUTES.toMillis(3)
    private val userInputSecond=TimeUnit.SECONDS.toMillis(0)


    val initialTotalTimeInMillis=userInputMinutes+userInputSecond
    var timeleft=mutableStateOf(initialTotalTimeInMillis)

    val sinitialTotalTimeInMillis = shortBreak+userInputSecond
    var stimeleft=mutableStateOf(sinitialTotalTimeInMillis)

    val linitialTotalTimeInMillis = longBreak+userInputSecond
    var ltimeleft=mutableStateOf(linitialTotalTimeInMillis)

    val countDownInterval=1000L

    var timerText= mutableStateOf(timeleft.value.timeFormat())

    val isPlaying= mutableStateOf(false)
    val ismainFinish= mutableStateOf(false)
    val isshortBfinish= mutableStateOf(false)
    val islongBfinish= mutableStateOf(false)
    val count= mutableStateOf(0)

    fun startCountDownTimer()= viewModelScope.launch {
        isPlaying.value=true

        countDownTimer=object:CountDownTimer(timeleft.value,
            countDownInterval){
            override fun onTick(currentTimeLeft:Long) {
                timerText.value = currentTimeLeft.timeFormat()
                timeleft.value= currentTimeLeft

            }

            override fun onFinish() {
                isPlaying.value=false
                ismainFinish.value=true
                countDownTimer?.cancel()
                timerText.value=initialTotalTimeInMillis.timeFormat()
                timeleft.value=initialTotalTimeInMillis

            }
        }.start()
    }
    //short break
    fun startBreakTimer()= viewModelScope.launch {
        isPlaying.value=true

        countDownTimer=object:CountDownTimer(stimeleft.value,
            countDownInterval){
            override fun onTick(currentTimeLeft:Long) {
                timerText.value = currentTimeLeft.timeFormat()
                timeleft.value= currentTimeLeft

            }

            override fun onFinish() {
                isPlaying.value=false
                isshortBfinish.value=true
                count.value++
                countDownTimer?.cancel()
                timerText.value=initialTotalTimeInMillis.timeFormat()
                timeleft.value=initialTotalTimeInMillis

            }
        }.start()
    }

    //long break
    fun startlbreakTimer()= viewModelScope.launch {
        isPlaying.value=true

        countDownTimer=object:CountDownTimer(ltimeleft.value,
            countDownInterval){
            override fun onTick(currentTimeLeft:Long) {
                timerText.value = currentTimeLeft.timeFormat()
                timeleft.value= currentTimeLeft

            }

            override fun onFinish() {
                isPlaying.value=false
                islongBfinish.value=true
                count.value=0
                countDownTimer?.cancel()
                timerText.value=initialTotalTimeInMillis.timeFormat()
                timeleft.value=initialTotalTimeInMillis

            }
        }.start()
    }
    fun stopCountDownTimer()=viewModelScope.launch {
        isPlaying.value=false
        countDownTimer?.cancel()
    }
    fun resetCountDownTimer()=viewModelScope.launch {
        isPlaying.value=false
        countDownTimer?.cancel()
        count.value=0
        timerText.value=initialTotalTimeInMillis.timeFormat()
        timeleft.value=initialTotalTimeInMillis
    }

}