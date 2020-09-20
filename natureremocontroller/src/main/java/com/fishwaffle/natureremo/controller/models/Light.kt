/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fishwaffle.natureremo.controller.NatureRemo.isIgnoreUnknown
import java.io.Serializable

/** 照明リモコンの情報
 * @param buttons ボタンリスト
 * @param state 状態
 * */
@JsonIgnoreProperties(ignoreUnknown = isIgnoreUnknown)
data class Light(var buttons: List<Button>? = null, var state: State? = null) : Serializable {

    /**
     * 照明ボタン情報
     * @param name ボタン名
     * @param image ボタンアイコン
     * @param label ラベル
     */
    @JsonIgnoreProperties(ignoreUnknown = isIgnoreUnknown)
    data class Button(override var name: String?, override var image: String?, override var label: String?) : ApplianceButton(name, image, label)

    /**
     * 照明ステータス
     * @param brightness  明るさの種類
     * @param power       電源 [on, off]
     * @param last_button 最後に使用したボタン
     *
     */
    @JsonIgnoreProperties(ignoreUnknown = isIgnoreUnknown)
    data class State(var brightness: String, var power: String, var last_button: String) : Serializable

}
