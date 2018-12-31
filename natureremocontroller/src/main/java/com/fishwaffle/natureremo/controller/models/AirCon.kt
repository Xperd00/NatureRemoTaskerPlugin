/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models

import java.io.Serializable
import java.util.*

class AirCon : Serializable {
    /** 指定可能は値範囲の情報  */
    var range: Range? = null
    /**
     * 温度の単位
     * c:摂氏
     * f:華氏
     */
    var tempUnit: String? = null

    override fun toString(): String {
        return "AirCon{" + "range=" + range + ", tempUnit='" + tempUnit + '\''.toString() + '}'.toString()
    }

    class Range {
        /** 各モード  */
        var modes: Modes? = null
        var fixedButtons: Array<String>? = null

        override fun toString(): String {
            return "Range{" + "modes=" + modes + ", fixedButtons=" + Arrays.toString(fixedButtons) + '}'.toString()
        }
    }

    class Modes : Serializable {
        /** 冷房  */
        var cool: AirConRangeMode? = null
        /** 暖房  */
        var warm: AirConRangeMode? = null
        /** ドライ  */
        var dry: AirConRangeMode? = null
        /** 送風?  */
        var blow: AirConRangeMode? = null
        /** オート  */
        var auto: AirConRangeMode? = null

        override fun toString(): String {
            return "Modes{" + "cool=" + cool + ", warm=" + warm + ", dry=" + dry + ", blow=" + blow + ", auto=" + auto + '}'.toString()
        }
    }

}



