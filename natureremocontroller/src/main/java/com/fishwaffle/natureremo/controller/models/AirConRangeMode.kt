/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models

import java.io.Serializable
import java.util.*

class AirConRangeMode : Serializable {
    /**温度 */
    var temp: Array<String>? = null
    /**風量 */
    var vol: Array<String>? = null
    /**風向き */
    var dir: Array<String>? = null

    override fun toString(): String {
        return "AirConRangeMode{" +
                "temp=" + Arrays.toString(temp) +
                ", vol=" + Arrays.toString(vol) +
                ", dir=" + Arrays.toString(dir) +
                '}'.toString()
    }
}
