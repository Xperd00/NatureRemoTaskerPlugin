/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models

import java.io.Serializable

/** センサー値  */
class SensorValue : Serializable {
    /** センサー値  */
    var `val`: Float = 0.toFloat()
    /** 作成日時  */
    var created_at: String? = null

    override fun toString(): String {
        return "SensorValue{" +
                "val=" + `val` +
                ", created_at='" + created_at + '\''.toString() +
                '}'.toString()
    }
}