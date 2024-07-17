package com.app.moviesapp.utils

import android.util.Log
import com.google.gson.Gson

fun <T> T.log(key: String = ""): T {
    Log.e("QuickLog", "$key: $this")
    return this
}

fun <T>T.toJson(): String{
    return Gson().toJson(this)
}