/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller


object Util {

    fun <T> requireNonNull(obj: T?, def: T): T {
        return obj ?: def

    }

}
