/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models

import java.io.Serializable

class AirConParams : Serializable {
    /**
     * 温度
     * 指定可能な範囲はAirConRangeMode参照
     */
    var temp: String? = null
    /**
     * モード
     * 冷房    :cool
     * 暖房    :warm
     * ドライ  :dry
     * 送風?   :blow
     * オート  :auto
     * 指定可能なモードはAirConRangeMode参照
     */
    var mode: String? = null
    /**
     * 風量
     * 指定可能な範囲はAirConRangeMode参照
     */
    var vol: String? = null
    /**
     * 風向き
     * 指定可能な範囲はAirConRangeMode参照
     */
    var dir: String? = null
    /**
     * 電源
     * ON :空文字
     * OFF:power-off
     */
    var button: String? = null
    /** 更新日時  */
    var updated_at: String? = null

    override fun toString(): String {
        return "AirConParams{" + "temp='" + temp + '\''.toString() + ", mode='" + mode + '\''.toString() + ", vol='" + vol + '\''.toString() + ", dir='" + dir + '\''.toString() + ", button='" + button + '\''.toString() + ", updated_at='" + updated_at + '\''.toString() + '}'.toString()
    }
}