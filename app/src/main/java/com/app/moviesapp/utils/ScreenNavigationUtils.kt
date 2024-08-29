package com.app.moviesapp.utils

import com.app.moviesapp.ui.Screens

fun Screens.withArgs() : ArgBuilder{
    return ArgBuilder(route)
}

class ArgBuilder(
    private var route: String
) {
    private var isFirst = true
    fun addArg(key: String, value: Any?): ArgBuilder {
        if (value == null) return this
        if (isFirst) {
            route += "?$key=$value"
            isFirst = false
        } else {
            route += "&$key=$value"
        }
        return this
    }

    fun route(): String {
        return route
    }

}