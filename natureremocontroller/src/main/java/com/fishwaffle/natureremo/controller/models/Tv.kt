/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fishwaffle.natureremo.controller.NatureRemo.isIgnoreUnknown
import java.io.Serializable

/** テレビリモコンの情報
 * @param buttons ボタンリスト
 * @param state 状態
 * */
@JsonIgnoreProperties(ignoreUnknown = isIgnoreUnknown)
data class Tv(var buttons: List<Button>? = null, var state: State? = null) : Serializable {

    /**
     * ボタン情報
     * @param name ボタン名
     * @param image ボタンアイコン
     * @param label ラベル
     */
    @JsonIgnoreProperties(ignoreUnknown = isIgnoreUnknown)
    data class Button(var name: String?, var image: String?, var label: String?) : Serializable

    /**
     * @param input
     * TODO APIリファレンスに無いので分からない
     * TOSHIBAのリモコンで「t」が取得できた
     * ボタン表示のレイアウトの識別記号？
     */
    @JsonIgnoreProperties(ignoreUnknown = isIgnoreUnknown)
    data class State(var input: String? = null) : Serializable

}
