package com.example.kaizensport.util


import android.text.format.DateFormat


object DateConverter {

    fun untilEvent(timeStamp: String): String {

        return if (timeStamp.isNotEmpty()){
            val untilDateTimeStamp = timeStamp.toLong()
            DateFormat.format("HH:mm:ss", untilDateTimeStamp).toString()
        }else{
            ""
        }

    }


}