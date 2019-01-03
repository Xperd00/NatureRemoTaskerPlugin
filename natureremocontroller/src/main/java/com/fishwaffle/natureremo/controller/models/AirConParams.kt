/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fishwaffle.natureremo.controller.NatureRemo.isIgnoreUnknown
import java.io.Serializable

/**
 * 指定可能な範囲はAirConRangeMode参照
 * @param temp 温度
 * @param mode モード
 *              冷房    :cool
 *              暖房    :warm
 *              ドライ  :dry
 *              送風    :blow
 *              オート  :auto
 * @param vol 風量
 * @param dir 風向き
 * @param button 電源
 *                ON :空文字
 *                OFF:power-off
 * @param updated_at 更新日時
 *
 */
@JsonIgnoreProperties(ignoreUnknown = isIgnoreUnknown)
data class AirConParams(var temp: String?, var mode: String?, var vol: String?, var dir: String?, var button: String?, var updated_at: String?) : Serializable