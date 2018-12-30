/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.taskerplugin

import android.content.Context
import android.content.Context.MODE_PRIVATE

const val TOKEN_KEY = "TOKEN_KEY"

fun saveToken(context: Context, token: String) {
    context.getSharedPreferences("natureremo", MODE_PRIVATE).edit().apply {
        putString(TOKEN_KEY, token)
        apply()
    }
}

fun getToken(context: Context): String {
    return context.getSharedPreferences("natureremo", MODE_PRIVATE).getString(TOKEN_KEY, "")!!
}