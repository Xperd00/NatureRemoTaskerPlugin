/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models

import java.io.Serializable
import java.util.*

/** テレビリモコンの情報  */
class Tv : Serializable {
    /** ボタンリスト  */
    var buttons: Array<Button>? = null

    var state: State? = null

    class Button : Serializable {
        /** ボタン名  */
        var name: String? = null
        /** ボタンアイコン  */
        var image: String? = null
        /** ラベル  */
        var label: String? = null

        override fun toString(): String {
            return "Button{" + "name='" + name + '\''.toString() + ", image='" + image + '\''.toString() + ", label='" + label + '\''.toString() + '}'.toString()
        }
    }

    class State : Serializable {
        /**
         * TODO APIリファレンスに無いので分からない
         * TOSHIBAのリモコンで「t」が取得できた
         * ボタン表示のレイアウトの識別記号？
         */
        var input: String? = null

        override fun toString(): String {
            return "State{" + "input='" + input + '\''.toString() + '}'.toString()
        }
    }

    override fun toString(): String {
        return "TV{" + "buttons=" + Arrays.toString(buttons) + ", state=" + state + '}'.toString()
    }
}
