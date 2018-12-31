/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models

import java.io.Serializable

class ApplianceModelAndParam : Serializable {
    /**プリセット情報 */
    var model: ApplianceModel? = null
    /**エアコンの設定状態 */
    var params: AirConParams? = null
}
