/*
 * Copyright (c) 2020 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fishwaffle.natureremo.controller.NatureRemo
import java.io.Serializable


@JsonIgnoreProperties(ignoreUnknown = NatureRemo.isIgnoreUnknown)
open class Command(open var type: Int) : Serializable {
    companion object {
        const val Signal = 0
        const val Button = 1
    }

    open fun getTitle() : String {
        return ""
    }
}