package com.example.productivity

sealed class navitem(
    var title:String, var icon: Int, var
    screen_route:String){
    object TimerS : navitem("Timer",R.drawable.clocks ,"first")
    object TodoS: navitem("Todo",R.drawable.notes,
        "second")
}
