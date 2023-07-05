package com.example.productivity

import java.util.concurrent.TimeUnit

object TimeForm {
    private const val FORMAT= "%02d:%02d"

    fun Long.timeFormat():String=String.format(
        FORMAT,
        TimeUnit.MILLISECONDS.toMinutes(this)%60,
        TimeUnit.MILLISECONDS.toSeconds(this)%60
    )
}