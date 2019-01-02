/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fishwaffle.natureremo.controller.isIgnoreUnknown
import java.io.Serializable

/**
 * @param model プリセット情報
 * @param params エアコンの設定状態
 */
@JsonIgnoreProperties(ignoreUnknown = isIgnoreUnknown)
data class ApplianceModelAndParam(var model: ApplianceModel?, var params: AirConParams?) : Serializable