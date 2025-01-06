package com.example.core.presentation

fun formatInstructions(text: String): String{
    var temp= text
    return temp.replace("\\r\\n","\n")
}