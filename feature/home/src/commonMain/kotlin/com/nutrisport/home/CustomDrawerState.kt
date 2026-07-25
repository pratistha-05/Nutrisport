package com.nutrisport.home

enum class CustomDrawerState{
    OPEN,CLOSED
}
fun CustomDrawerState.toggle():CustomDrawerState =
    when(this){
        CustomDrawerState.OPEN->CustomDrawerState.CLOSED
        CustomDrawerState.CLOSED->CustomDrawerState.OPEN
    }
