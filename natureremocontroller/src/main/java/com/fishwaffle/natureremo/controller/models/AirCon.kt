/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fishwaffle.natureremo.controller.NatureRemo.isIgnoreUnknown
import java.io.Serializable

/**
 * @param range 指定可能は値範囲の情報
 * @param tempUnit 温度の単位
 *                  c:摂氏
 *                  f:華氏
 */
@JsonIgnoreProperties(ignoreUnknown = isIgnoreUnknown)
data class AirCon(var range: Range?, var tempUnit: String?) : Serializable {
    /**
     * @param modes 各モード
     * @param fixedButtons
     */
    @JsonIgnoreProperties(ignoreUnknown = isIgnoreUnknown)
    data class Range(var modes: Modes?, var fixedButtons: List<String>?) : Serializable

    /**
     * @param cool 冷房
     * @param warm 暖房
     * @param dry ドライ
     * @param blow 送風
     * @param auto オート
     */
    @JsonIgnoreProperties(ignoreUnknown = isIgnoreUnknown)
    data class Modes(var cool: AirConRangeMode?, var warm: AirConRangeMode?, var dry: AirConRangeMode?, var blow: AirConRangeMode?, var auto: AirConRangeMode?) : Serializable

}



