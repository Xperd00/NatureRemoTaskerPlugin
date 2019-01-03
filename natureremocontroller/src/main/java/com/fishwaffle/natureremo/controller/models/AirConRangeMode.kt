/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fishwaffle.natureremo.controller.NatureRemo.isIgnoreUnknown
import java.io.Serializable

/**
 * @param temp 温度
 * @param vol 風量
 * @param dir 風向き
 */
@JsonIgnoreProperties(ignoreUnknown = isIgnoreUnknown)
data class AirConRangeMode(var temp: List<String>?, var vol: List<String>?, var dir: List<String>?) : Serializable